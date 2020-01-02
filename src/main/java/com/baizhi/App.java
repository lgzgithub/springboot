package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("I m a boy");
        System.out.println("I m a new boy");
    }
}
