package com.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@tk.mybatis.spring.annotation.MapperScan("com.example.dao")
@org.mybatis.spring.annotation.MapperScan("com.example.dao")
@SpringBootApplication
public class YingxueApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxueApplication.class, args);
    }

}
