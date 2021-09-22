package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
@Data
@Table(name = "yx_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Integer id;

    private String phone;

    private String name;

    private String signature;
@Column(name = "avatar_url")
    private String avatarUrl;
@Column(name = "wechat_openid")
    private String wechatOpenid;

    private Integer status;
@Column(name = "create_time")
@JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
@Column(name = "update_time")
    private Date updateTime;
@Column(name = "delete_time")
    private Date deleteTime;
    private String sex;
    @Transient
    private Integer score;

}