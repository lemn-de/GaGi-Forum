package com.forum.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@MapperScan("com.forum.common.dao")
public class ForumRestApplication {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        InputStream in = ForumRestApplication.class.getClassLoader().getResourceAsStream("rest.properties");
        properties.load(in);
        SpringApplication app = new SpringApplication(ForumRestApplication.class);
        app.setDefaultProperties(properties);
        app.run(args);
    }

}
