package com.xishui.beeger.datap.mysql.constant;

public enum PluginStatus {
    //1:活跃 上线状态 可用 2:下线状态  3:在线，禁用
    ALIVE_ONLINE(1, "在线 可用"),
    OFFLINE(2, "下线 不可用"),
    ONLINE_UNUSE(3, "在线 不可用");

    private int status;
    private String description;

    PluginStatus(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public static String desc(int status){
        for(PluginStatus pluginStatus: PluginStatus.values()){
            if(pluginStatus.getStatus() == status){
                return pluginStatus.getDescription();
            }
        }
        return "未知状态";
    }
    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
