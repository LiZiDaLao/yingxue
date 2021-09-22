package com.example.dao;

import com.example.entity.Admin;
import tk.mybatis.mapper.common.Mapper;

public interface AdminDao{
   Admin selectByName(String username);
   Admin selectById(Integer id);
}
