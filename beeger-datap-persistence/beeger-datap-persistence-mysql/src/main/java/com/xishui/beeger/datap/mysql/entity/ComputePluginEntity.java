package com.xishui.beeger.datap.mysql.entity;

import com.xishui.beeger.datap.mysql.config.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "bg_compute_plugin")
@Data
public class ComputePluginEntity extends BaseEntity {
    /**
     * 计算插件model名
     */
    @Column(name = "model_name")
    private String modelName;
    @Column(name = "model_name_cn")
    private String modelNameCn;
    /**
     * 计算插件描述
     */
    @Column(name = "model_desc")
    private String modelDesc;
    /**
     * 计算插件的真正执行类(全)
     */
    @Column(name = "model_cls")
    private String modelCls;
    /**
     * 计算插件有哪些暴露的计算方法，JSON<List<Methods>>
     */
    @Column(name = "model_methods")
    private String modelMethods;
    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;
    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;
    /**
     * 状态 1:活跃 上线状态 可用 2:下线状态  3:在线，禁用
     */
    @Column(name = "model_status")
    private int modelStatus;
    /**
     * 当前运行中的活跃的机器列表 JSON<List<String>>
     */
    @Column(name = "alive_machine")
    private String aliveMachine;
    /**
     * 计算模型服务类型：REMOTE,LOCAL
     */
    @Column(name = "model_type")
    private String modelType;
    @Column(name = "plugin_jar_path")
    private String pluginJarPath;
}
