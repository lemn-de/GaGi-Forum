package com.forum.common;

import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.forum.common.dao")
public class ForumCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumCommonApplication.class, args);
    }



}
