package com.xishui.beeger.datap.model;

import lombok.Getter;

public enum ModelType {
    REMOTE("REMOTE"),
    LOCAL("LOCAL");
    @Getter
    private String type;

    ModelType(String type) {
        this.type = type;
    }
}
