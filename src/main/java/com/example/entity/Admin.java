package com.example.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Admin {

  private Integer id;
  private String username;
  private String password;
  private Date createTime;
  private Date updateTime;
  private Date deleteTime;



}
