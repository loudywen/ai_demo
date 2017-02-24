package com.devon.demo.main;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
@ComponentScan({"com.devon.demo.main.*"})
public class AiDemoApplication implements ApplicationContextAware {


    private static ApplicationContext ac;

    public static void main(String[] args) {
//		System.setProperty("javax.net.ssl.trustStoreType", "jks");
//		System.setProperty("javax.net.ssl.trustStore", "/home/ubuntu/diwen/cacerts.jks");
        SpringApplication.run(AiDemoApplication.class, args);
    }

    public static ApplicationContext getApplicationContext() {
        return ac;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }
}
