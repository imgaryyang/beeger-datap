package com.xishui.beeger.datap.mysql;

import com.xishui.beeger.datap.mysql.config.druid.DataSourceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = DataSourceConfiguration.class)
public class PersistenceMysqlApplication {

    public static void main(String... args) {
        ConfigurableApplicationContext context = SpringApplication.run(PersistenceMysqlApplication.class,args);
//        for(String beanName :context.getBeanDefinitionNames()){
//            System.out.println(beanName);
//        }
    }
}
