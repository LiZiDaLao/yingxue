package com.example.controller;

import com.example.service.AdminService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("admin")
public class AdminController {
  @Resource
    AdminService adminService;
  @PostMapping("getImageCode")
    public HashMap<String,Object> getImageCode(){
    return adminService.getImg();
  }
}
