package com.xishui.beeger.datap.web.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PluginMachineViewModel implements Serializable {

    private String machineIP;

    private String statusDesc;

    private int status;

    private String createDate;

    private String updateDate;

    private Long id;
}
