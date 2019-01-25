package com.xishui.beeger.datap.rest;

import com.xishui.beeger.datap.mysql.config.druid.DataSourceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = DataSourceConfiguration.class)
public class BeegerDatapRestApplication {

    public static void  main(String... args){
        SpringApplication.run(BeegerDatapRestApplication.class,args);
    }
}
