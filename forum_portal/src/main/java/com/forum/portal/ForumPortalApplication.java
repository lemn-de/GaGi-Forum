package com.forum.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class ForumPortalApplication {

    public static void main(String[] args) throws IOException {
//        new SpringApplicationBuilder(ForumPortalApplication.class)
//                .properties("spring.config.name:portal.properties")
//                .build()
//                .run();
//        SpringApplication.run(ForumPortalApplication.class, args);

        Properties properties = new Properties();
        InputStream in = ForumPortalApplication.class.getClassLoader().getResourceAsStream("portal.properties");
        properties.load(in);
        SpringApplication app = new SpringApplication(ForumPortalApplication.class);
        app.setDefaultProperties(properties);
        app.run(args);
    }

}
