package com.xishui.beeger.datap.web;

import com.xishui.beeger.datap.mgrment.container.ContainerMgr;
import com.xishui.beeger.datap.mysql.config.druid.DataSourceConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = DataSourceConfiguration.class)
public class BeegerDatapWebApplication extends SpringBootServletInitializer implements CommandLineRunner {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BeegerDatapWebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BeegerDatapWebApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ContainerMgr.MGR.starterContainer();
                }
            });
            thread.setName("Compute-Netty-Server-Thread");
            thread.start();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    ContainerMgr.MGR.stopContainer();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
