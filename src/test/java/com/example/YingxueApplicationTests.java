package com.example;

import com.example.dao.AdminDao;
import com.example.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class YingxueApplicationTests {
@Resource
    AdminDao adminDao;
    @Test
    void contextLoads() {
//        Admin select = adminDao.select();
//        System.out.println(select);
    }

}
