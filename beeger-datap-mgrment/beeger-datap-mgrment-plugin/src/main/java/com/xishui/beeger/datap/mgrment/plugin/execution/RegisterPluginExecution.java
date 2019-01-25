package com.xishui.beeger.datap.mgrment.plugin.execution;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.currency.spring.SpringContextUtil;
import com.xishui.beeger.datap.mgrment.plugin.spi.PluginMessageExecution;
import com.xishui.beeger.datap.model.ParamKeys;
import com.xishui.beeger.datap.model.Params;
import com.xishui.beeger.datap.model.ResponseCode;
import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.model.compute.ComputeDescriptionModel;
import com.xishui.beeger.datap.mysql.constant.PluginStatus;
import com.xishui.beeger.datap.mysql.entity.ComputePluginEntity;
import com.xishui.beeger.datap.mysql.mapper.ComputePluginMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 提供注册计算插件的消息处理器
 */
public class RegisterPluginExecution implements PluginMessageExecution {
    @Override
    public Result<String> execution(Params param, Map<String, Object> extendParams) {
        if (!param.containKey(ParamKeys.PLUGIN_REGISTER_KEY)) {
            return Result.ofFailure("ComputeModel Description is null.");
        }
        if (!param.containKey(ParamKeys.REQUEST_IP)) {
            return Result.ofFailure("ComputeModel machine ip is null.");
        }
        try {
            ComputePluginMapper computePluginMapper = SpringContextUtil.getBean(ComputePluginMapper.class);
            List<ComputeDescriptionModel> computeDescriptionModels = JSON.parseArray(param.getValueString(ParamKeys.PLUGIN_REGISTER_KEY), ComputeDescriptionModel.class);
            String machineIP = param.getValueString(ParamKeys.REQUEST_IP);

            String modelType = param.getValueString(ParamKeys.MODEL_TYPE);
            String jarPath = param.containKey(ParamKeys.PLUGIN_JAR_PATH) ?
                    param.getValueString(ParamKeys.PLUGIN_JAR_PATH) : null;
            computeDescriptionModels.forEach(model -> {
                List<ComputePluginEntity> computePluginEntities = computePluginMapper.selectByModelNameAndMachineIP(
                        model.getComputeModelName(), machineIP);
                if (null == computePluginEntities || computePluginEntities.size() <= 0) {
                    computePluginMapper.insert(convert(model, machineIP, modelType, jarPath));
                } else {
                    for (final ComputePluginEntity entity : computePluginEntities) {
                        if (entity.getModelStatus() == PluginStatus.ALIVE_ONLINE.getStatus()) {
                        } else if (entity.getModelStatus() == PluginStatus.OFFLINE.getStatus()) {
                            entity.setModelStatus(PluginStatus.ONLINE_UNUSE.getStatus());

                        }
                        entity.setModelName(model.getComputeModelName());
                        entity.setModelCls(model.getComputeModelClass());
                        entity.setModelNameCn(model.getComputeModelNameCn());
                        entity.setModelDesc(model.getComputeModelDescription());
                        entity.setUpdateDate(new Date());
                        entity.setModelMethods(JSON.toJSONString(model.getComputeDescriptionMethods()));
                        computePluginMapper.updateByPrimaryKey(entity);
                    }
                }
            });
        } catch (Exception e) {
            return Result.ofFailure(e.getMessage());
        }
        // 写zk
        return Result.ofSuccess(ResponseCode.SUCCESS);
    }

    private ComputePluginEntity convert(ComputeDescriptionModel computeDescriptionModel, String ip, String modelType,
                                        String jarPath) {
        ComputePluginEntity computePluginEntity = new ComputePluginEntity();
        computePluginEntity.setUpdateDate(new Date());
        computePluginEntity.setModelName(computeDescriptionModel.getComputeModelName());
        computePluginEntity.setModelStatus(PluginStatus.ONLINE_UNUSE.getStatus());
        computePluginEntity.setModelDesc(computeDescriptionModel.getComputeModelDescription());
        computePluginEntity.setModelCls(computeDescriptionModel.getComputeModelClass());
        computePluginEntity.setCreateDate(new Date());
        computePluginEntity.setAliveMachine(ip);
        computePluginEntity.setModelType(modelType);
        computePluginEntity.setPluginJarPath(jarPath);
        computePluginEntity.setModelMethods(JSON.toJSONString(computeDescriptionModel.getComputeDescriptionMethods()));
        computePluginEntity.setModelNameCn(computeDescriptionModel.getComputeModelNameCn());
        return computePluginEntity;
    }
}
