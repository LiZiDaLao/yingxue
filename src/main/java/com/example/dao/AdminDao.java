package com.example.dao;

import com.example.entity.Admin;

public interface AdminDao {
   Admin selectByName(String username);
}
