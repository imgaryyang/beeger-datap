package com.xishui.beeger.datap.plugin.example;

import com.xishui.beeger.datap.plugin.api.annotation.ValueKey;
import lombok.Data;

@Data
public class ExampleUser {
    @ValueKey(key = "id")
    private Long id;
    @ValueKey(key = "user_name")
    private String userName;
    @ValueKey(key = "phone")
    private String phone;
    @ValueKey(key = "age")
    private Integer age;
}
