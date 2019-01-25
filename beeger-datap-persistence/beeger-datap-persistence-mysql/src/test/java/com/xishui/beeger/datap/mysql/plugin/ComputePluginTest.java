package com.xishui.beeger.datap.mysql.plugin;

import com.xishui.beeger.datap.mysql.BaseTest;
import com.xishui.beeger.datap.mysql.entity.ComputePluginEntity;
import com.xishui.beeger.datap.mysql.mapper.ComputePluginMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ComputePluginTest extends BaseTest{
    @Autowired
    private ComputePluginMapper computePluginMapper;
    @Test
    public void testAddComputePlugin(){
        ComputePluginEntity computePluginEntity = new ComputePluginEntity();
        computePluginEntity.setAliveMachine("181.12.12.123:201");
        computePluginEntity.setCreateDate(new Date());
        computePluginEntity.setModelCls("com.xx.dd");
        computePluginEntity.setModelDesc("test-model");
        computePluginEntity.setModelName("test-model");
        computePluginEntity.setModelStatus(1);
        computePluginEntity.setUpdateDate(new Date());
        computePluginMapper.insert(computePluginEntity);
    }
}
