package com.xishui.beeger.datap.plugin.api.scan;


import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.model.compute.ComputeDescriptionMethod;
import com.xishui.beeger.datap.model.compute.ComputeDescriptionModel;
import com.xishui.beeger.datap.netty.common.Holders;
import com.xishui.beeger.datap.plugin.api.BeegerCompute;
import com.xishui.beeger.datap.plugin.api.annotation.ComputeMethod;
import com.xishui.beeger.datap.plugin.api.annotation.ComputeModel;
import com.xishui.beeger.datap.plugin.api.starter.ComputeStarter;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum PluginScanner {
    SCANNER;
    private final InternalLogger logger = InternalLoggerFactory.getInstance(ComputeStarter.class);

    /**
     * 扫描制定package下的所有的类，如果没有制定，则默认扫描根目录下
     * 实现类BeegerCompute接口的实现类
     *
     * @param packagePath
     * @return
     */
    public List<ComputeDescriptionModel> scanner(String packagePath) {
        Reflections reflections = new Reflections(packagePath.replace(".", "/"));
        Set<Class<?>> allComputes = reflections.getTypesAnnotatedWith(ComputeModel.class);
        if (null == allComputes || allComputes.size() <= 0) {
            throw new RuntimeException("NON-computer by Define");
        }
        Set<Class<?>> matchComputes = matchCompute(allComputes);
        if (null == matchComputes || matchComputes.size() <= 0) {
            throw new RuntimeException("Non-computer match BeegerCompute");
        }
        final List<ComputeDescriptionModel> matchComputeDescs = new ArrayList<>();
        matchComputes.forEach(matchCompute -> {
            final ComputeDescriptionModel computeDescriptionModel = parseCompute(matchCompute);
            if (null != computeDescriptionModel) {
                String jsonComputeModel = JSON.toJSONString(computeDescriptionModel);
                Holders.putHolderValue(computeDescriptionModel.getComputeModelName(), jsonComputeModel);
                matchComputeDescs.add(computeDescriptionModel);
            }
        });
        logger.info("PluginScanner Completed:" + JSON.toJSONString(matchComputeDescs));
        return matchComputeDescs;
    }

    /**
     * 做数据过滤
     *
     * @param loadedComputes
     * @return
     */
    private Set<Class<?>> matchCompute(Set<Class<?>> loadedComputes) {
        Set<Class<?>> matchComputes = new HashSet<>();
        loadedComputes.forEach(loaded -> {
            //不是接口和抽象类
            if (!loaded.isInterface() && !Modifier.isAbstract(loaded.getModifiers())) {
                //实现类BeegerCompute接口
                if (BeegerCompute.class.isAssignableFrom(loaded)) {
                    matchComputes.add(loaded);
                }
            }
        });
        return matchComputes;
    }

    private ComputeDescriptionModel parseCompute(Class<?> computeCls) {
        // step 1:parse class anno
        ComputeModel computeModel = computeCls.getAnnotation(ComputeModel.class);
        if (null == computeModel) {
            return null;
        }
        ComputeDescriptionModel computeDescriptionModel = new ComputeDescriptionModel();
        computeDescriptionModel.setComputeModelClass(computeCls.getName());
        computeDescriptionModel.setComputeModelDescription(computeModel.modelDescription());
        computeDescriptionModel.setComputeModelName(computeModel.modelName());
        computeDescriptionModel.setComputeModelNameCn(computeModel.modelCnName());
        Method[] methods = computeCls.getMethods();

        if (null != methods && methods.length > 0) {
            final List<ComputeDescriptionMethod> computeDescriptionMethods = new ArrayList<>();
            for (final Method method : methods) {
                ComputeMethod computeMethod = method.getAnnotation(ComputeMethod.class);
                if (null == computeMethod) {
                    continue;
                }
                ComputeDescriptionMethod computeDescriptionMethod = new ComputeDescriptionMethod();
                computeDescriptionMethod.setComputeMethodDescription(computeMethod.methodDescription());
                computeDescriptionMethod.setComputeMethodName(computeMethod.methodName());
                computeDescriptionMethod.setRealMethodName(method.getName());
                computeDescriptionMethod.setComputeRequestParams(computeMethod.requestParams());
                computeDescriptionMethod.setComputeResponseValue(computeMethod.responseValue());
                computeDescriptionMethods.add(computeDescriptionMethod);
            }
            computeDescriptionModel.setComputeDescriptionMethods(computeDescriptionMethods);
        }
        return computeDescriptionModel;
    }
}
