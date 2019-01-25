package com.xishui.beeger.datap.mysql.mapper;

import com.xishui.beeger.datap.mysql.config.base.BaseMapper;
import com.xishui.beeger.datap.mysql.entity.ComputePluginEntity;
import com.xishui.beeger.datap.mysql.model.ComputePluginDistinctModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ComputePluginMapper extends BaseMapper<ComputePluginEntity> {

    @Results(id = "selectByModelNameAndStatus", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "modelName", column = "model_name"),
            @Result(property = "modelDesc", column = "model_desc"),
            @Result(property = "modelCls", column = "model_cls"),
            @Result(property = "modelMethods", column = "model_methods"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "modelStatus", column = "model_status"),
            @Result(property = "aliveMachine", column = "alive_machine"),
            @Result(property = "updateDate", column = "update_date")
    })
    @Select("select * from bg_compute_plugin where model_name = #{modelName} and model_status = #{status}")
    List<ComputePluginEntity> selectByModelNameAndStatus(@Param("modelName") String modelName, @Param("status") int status);

    @Results(id = "selectByModelNameAndMachineIP", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "modelName", column = "model_name"),
            @Result(property = "modelNameCn", column = "model_name_cn"),
            @Result(property = "modelDesc", column = "model_desc"),
            @Result(property = "modelCls", column = "model_cls"),
            @Result(property = "modelMethods", column = "model_methods"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "modelStatus", column = "model_status"),
            @Result(property = "aliveMachine", column = "alive_machine"),
            @Result(property = "updateDate", column = "update_date")
    })
    @Select("select * from bg_compute_plugin where model_name = #{modelName} and alive_machine = #{machineIP}")
    List<ComputePluginEntity> selectByModelNameAndMachineIP(@Param("modelName") String modelName,@Param("machineIP") String machineIP);
    @Results(id = "selectByModelName", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "modelName", column = "model_name"),
            @Result(property = "modelNameCn", column = "model_name_cn"),
            @Result(property = "modelDesc", column = "model_desc"),
            @Result(property = "modelCls", column = "model_cls"),
            @Result(property = "modelMethods", column = "model_methods"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "modelStatus", column = "model_status"),
            @Result(property = "aliveMachine", column = "alive_machine"),
            @Result(property = "updateDate", column = "update_date")
    })
    @Select("select * from bg_compute_plugin where model_name = #{modelName}")
    List<ComputePluginEntity> selectByModelName(@Param("modelName") String modelName);

    @Results(id = "", value = {
            @Result(column = "modelName", property = "modelName"),
            @Result(column = "aliveuse", property = "aliveUse"),
            @Result(column = "unlive", property = "unAlive"),
            @Result(column = "aliveunuse", property = "aliveUnUse")
    })
    @Select("select t.modelName,max(CASE t.modelStatus when 1 then t.cnt else 0 end) aliveuse," +
            "max(CASE t.modelStatus when 2 then t.cnt else 0 end) unlive," +
            "max(CASE t.modelStatus when 3 then t.cnt else 0 end) aliveunuse " +
            "from (select count(1) cnt,model_name modelName,model_status modelStatus " +
            "from bg_compute_plugin group by model_name,model_status) t group by t.modelName;")
    List<ComputePluginDistinctModel> selectDistinctModelName();

    @Update("update bg_compute_plugin set model_status=#{status} where id = #{id}")
    void updatePluginStatus(@Param("id") Long id,@Param("status") int status);
}
