package com.hsp.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SuppressWarnings("ALL")
@SpringBootApplication
//@EntityScan(basePackages = {"com.hsp.blog.po"})
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

public class BlogApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
