package com.xishui.beeger.datap.web.controller.plugin;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.currency.zk.ZkNode;
import com.xishui.beeger.datap.currency.zk.holder.ZkClient;
import com.xishui.beeger.datap.model.IPModel;
import com.xishui.beeger.datap.model.compute.ComputeDescriptionMethod;
import com.xishui.beeger.datap.model.compute.ComputeRequestModel;
import com.xishui.beeger.datap.model.compute.ComputeResponseModel;
import com.xishui.beeger.datap.mysql.constant.PluginStatus;
import com.xishui.beeger.datap.mysql.entity.ComputePluginEntity;
import com.xishui.beeger.datap.mysql.mapper.ComputePluginMapper;
import com.xishui.beeger.datap.mysql.model.ComputePluginDistinctModel;
import com.xishui.beeger.datap.rest.processor.ComputeProcessor;
import com.xishui.beeger.datap.web.config.PluginLoaderConfig;
import com.xishui.beeger.datap.web.model.PluginLoaderModel;
import com.xishui.beeger.datap.web.model.PluginMachineViewModel;
import com.xishui.beeger.datap.web.model.PluginViewModel;
import com.xishui.beeger.datap.web.support.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/plugin")
public class ComputePluginController {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private ComputePluginMapper computePluginMapper;
    @Autowired
    private PluginLoaderConfig pluginLoaderConfig;

    @RequestMapping(value = "/compute", method = RequestMethod.GET)
    public ModelAndView pluginList(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("/pages/plugin/pluginList");
        final List<ComputePluginEntity> computePluginEntitys = computePluginMapper.selectAll();

        final List<ComputePluginDistinctModel> distinctModels = computePluginMapper.selectDistinctModelName();

        if (null == distinctModels || distinctModels.isEmpty()) {
            view.addObject("plugins", new ArrayList<PluginViewModel>());
        } else {
            view.addObject("plugins", transferViewModel(distinctModels));
        }
        return view;
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public ModelAndView forwadAdded() {
        ModelAndView view = new ModelAndView("/pages/plugin/addPlugin");
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "/applyAdded", method = RequestMethod.POST)
    public String applyAdded(@RequestParam(name = "jarName", required = true) String jarName) {
        try {
            String pluginJarPath = pluginLoaderConfig.getJarPath() + "/" + jarName;
            //往zk写添加组件事件
            String zkNodeName = jarName.substring(0, jarName.lastIndexOf("."));
            ZkClient.CLIENT.createNodeExist(ZkNode.PLUGIN_PATH + "/" + zkNodeName, JSON.toJSONString(Arrays.asList(
                    new PluginLoaderModel[]{new PluginLoaderModel(IPModel.IP.localIP(), pluginJarPath)}
            )));
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/cleanPlugin", method = RequestMethod.POST)
    public String cleanPlugin(@RequestParam(name = "jarName", required = true) String jarName) {
        try {
            String zkNodeName = ZkNode.PLUGIN_PATH + "/" + jarName.substring(0, jarName.lastIndexOf("."));
            ZkClient.CLIENT.checkNodeExistAndDelete(zkNodeName);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView pluginDetail(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) String modelName) {
        ModelAndView view = new ModelAndView("/pages/plugin/pluginDetail");
        List<ComputePluginEntity> computePluginEntities = computePluginMapper.selectByModelName(modelName);
        if (null == computePluginEntities || computePluginEntities.size() <= 0) {
            view.addObject("plugin", new PluginViewModel());
        } else {
            view.addObject("plugin", pluginDetailViewModels(computePluginEntities));
        }
        return view;
    }

    @RequestMapping(value = "/updatePluginStatus", method = RequestMethod.POST)
    @ResponseBody
    public Result updatePluginStatus(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(required = true) String id, @RequestParam(required = true) String status) {
        if (null == id || null == status || "".equals(id) || "".equals(status)) {
            return Result.fail("请求参数为空.");
        }
        computePluginMapper.updatePluginStatus(Long.valueOf(id), Integer.valueOf(status));
        return Result.success();
    }

    @RequestMapping(value = "/testPluginCompute", method = RequestMethod.POST)
    @ResponseBody
    public ComputeResponseModel testPluginCompute(@RequestParam(required = true) String testRequest, @RequestParam(required = true) String machineIp) {
        try {
            ComputeRequestModel computeRequestModel = JSON.parseObject(testRequest, ComputeRequestModel.class);
            computeRequestModel.setComputeIp(machineIp);
            return new ComputeProcessor().processor(computeRequestModel);
        } catch (Exception e) {
            return ComputeResponseModel.ofFailure("操作失败:" + e.getMessage());
        }
    }

    private List<PluginViewModel> transferViewModel(List<ComputePluginDistinctModel> distinctModels) {
        final List<PluginViewModel> pluginViewModels = new ArrayList<>();
        distinctModels.forEach(distinct -> {
            PluginViewModel model = new PluginViewModel();
            model.setPluginName(distinct.getModelName());
            model.setAliveCount(distinct.getAliveUse());
            model.setUnliveCount(distinct.getUnAlive());
            model.setAliveUnUseCount(distinct.getAliveUnUse());
            model.setMachineCount(distinct.getAliveUnUse() + distinct.getAliveUse() + distinct.getUnAlive());
            if (distinct.getAliveUse() > 0) {
                model.setDistinctStatus("可用");
                model.setStatus(1);
            }
            if (distinct.getAliveUse() > 0 && (distinct.getAliveUnUse() > 0 || distinct.getUnAlive() > 0)) {
                model.setDistinctStatus("部分可用");
                model.setStatus(2);
            }
            if (distinct.getAliveUse() <= 0) {
                model.setDistinctStatus("不可用");
                model.setStatus(3);
            }
            pluginViewModels.add(model);
        });
        return pluginViewModels;
    }

    private PluginViewModel pluginDetailViewModels(List<ComputePluginEntity> computePluginEntities) {
        final List<PluginMachineViewModel> pluginMachineViewModels = new ArrayList<>();
        computePluginEntities.forEach(machine -> {
            PluginMachineViewModel machineViewModel = new PluginMachineViewModel();
            machineViewModel.setMachineIP(machine.getAliveMachine());
            machineViewModel.setStatus(machine.getModelStatus());
            machineViewModel.setStatusDesc(PluginStatus.desc(machine.getModelStatus()));
            machineViewModel.setCreateDate(format.format(machine.getCreateDate()));
            machineViewModel.setUpdateDate(format.format(machine.getUpdateDate()));
            machineViewModel.setId(machine.getId());
            pluginMachineViewModels.add(machineViewModel);
        });
        PluginViewModel model = new PluginViewModel();
        ComputePluginEntity computePluginEntity = computePluginEntities.get(0);
        model.setPluginMachineViewModels(pluginMachineViewModels);
        model.setPluginName(computePluginEntity.getModelName());
        model.setModelDesc(computePluginEntity.getModelDesc());
        model.setPluginNameCn(computePluginEntity.getModelNameCn());
        final List<ComputeDescriptionMethod> computeDescriptionMethods = JSON.parseArray(computePluginEntity.getModelMethods(), ComputeDescriptionMethod.class);
        model.setMethods(computeDescriptionMethods);
        model.setMachineCount(computePluginEntities.size());
        return model;
    }
}
