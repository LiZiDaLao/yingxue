package com.example.service;

import com.example.dto.AdminDTO;

import java.util.HashMap;

public interface AdminService {
    HashMap<String ,Object> getImg();
    HashMap<String ,Object> login(AdminDTO adminDTO);
}
