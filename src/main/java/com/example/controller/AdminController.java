package com.example.controller;

import com.example.dto.AdminDTO;
import com.example.service.AdminService;
import org.springframework.web.bind.annotation.*;

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
  @PostMapping("login")
  public HashMap<String,Object>login(@RequestBody AdminDTO adminDTO){
   return adminService.login(adminDTO);

  }


}
