package com.xishui.beeger.datap.web.model;

import com.xishui.beeger.datap.model.compute.ComputeDescriptionMethod;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PluginViewModel implements Serializable {
    //根据name与desc 分组
    private String pluginName;
    //根据pluginName distinct之后的状态: 全部可用  部分可用  不可用
    private String distinctStatus;
    private String pluginNameCn;
    private int status;//1 全部可用 2 部分可用 3 不可用
    //有多少机器在提供计算服务
    private int machineCount;
    //多少机器是存活的可用的
    private int aliveCount;
    //多少机器是不存活的
    private int unliveCount;
    //多少机器是存活不可用的
    private int aliveUnUseCount;

    private String modelDesc;

    private List<PluginMachineViewModel> pluginMachineViewModels;

    private List<ComputeDescriptionMethod> methods;

}
