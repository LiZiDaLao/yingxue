package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
  private Integer id;
  private String username;
  private String password;
  private Date createTime;
  private Date updateTime;
  private Date deleteTime;



}
