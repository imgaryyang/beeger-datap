package com.xishui.beeger.datap;

import com.xishui.beeger.datap.currency.zk.ZkProperties;
import com.xishui.beeger.datap.currency.zk.ZkStarted;

public class ZkTest {

    public static void main(String... args){
        ZkProperties zkProperties = new ZkProperties();
//        zkProperties.setConnect();
//        zkProperties.setConnectionTimeOut();
//        zkProperties.setSessionTimeOut();
        try {
            ZkStarted.ZK.started(zkProperties);
            for(;;);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
