package com.xishui.beeger.datap.plugin.example;

public class ExampleMain {
    public static void main(String... args) {
        try {
            new ExampleProviderStarter().startProvider();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
