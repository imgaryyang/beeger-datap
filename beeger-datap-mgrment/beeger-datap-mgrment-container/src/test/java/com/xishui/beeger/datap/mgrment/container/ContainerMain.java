package com.xishui.beeger.datap.mgrment.container;

public class ContainerMain {

    public static void main(String... args){
        try {
            ContainerMgr.MGR.starterContainer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
