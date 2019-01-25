package com.xishui.beeger.datap.web.config;

import jetbrick.template.web.springmvc.JetTemplateViewResolver;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;

/**
 * @description
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    @Bean
    public JetTemplateViewResolver viewResolver() {
        JetTemplateViewResolver jetTemplateViewResolver = new JetTemplateViewResolver();
        jetTemplateViewResolver.setOrder(1);
        jetTemplateViewResolver.setContentType("text/html; charset=utf-8");
        jetTemplateViewResolver.setSuffix(".html");
        jetTemplateViewResolver.setConfigLocation("classpath:/jetbrick-template.properties");
        Properties configProperties = new Properties();
        configProperties.setProperty("jetx.input.encoding", "UTF-8");
        configProperties.setProperty("jetx.output.encoding", "UTF-8");
        jetTemplateViewResolver.setConfigProperties(configProperties);
        return jetTemplateViewResolver;
    }

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new VisitCountListener());
        return servletListenerRegistrationBean;
    }
}
