package com.example.service;

import com.example.dto.AdminDTO;
import com.example.entity.Admin;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

public interface AdminService {
    HashMap<String ,Object> getImg();
    HashMap<String ,Object> login(@RequestBody AdminDTO adminDTO);
    Admin queryToken(String token);
    HashMap<String,Object>logout(String token);
}
