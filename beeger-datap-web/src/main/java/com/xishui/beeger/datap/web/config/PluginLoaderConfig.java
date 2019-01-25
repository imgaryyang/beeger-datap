package com.xishui.beeger.datap.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "compute.plugin.config")
@Data
@Component
public class PluginLoaderConfig {
    private String jarPath;
}
