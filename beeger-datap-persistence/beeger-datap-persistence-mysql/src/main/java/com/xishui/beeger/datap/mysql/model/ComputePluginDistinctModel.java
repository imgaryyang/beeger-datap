package com.xishui.beeger.datap.mysql.model;

import lombok.Data;

@Data
public class ComputePluginDistinctModel {
    private String modelName;

    private int aliveUse;

    private int unAlive;

    private int aliveUnUse;
}
