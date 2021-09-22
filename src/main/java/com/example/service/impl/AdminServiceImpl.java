package com.example.service.impl;

import com.example.dao.AdminDao;
import com.example.dto.AdminDTO;
import com.example.entity.Admin;
import com.example.service.AdminService;
import com.example.util.ImageCodeUtil;
import com.example.util.UUIDUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

  @Resource
  StringRedisTemplate stringRedisTemplate;
  @Resource
    AdminDao adminDao;

    @Override
    public HashMap<String,Object> getImg() {
        //生成验证码随机数
        String imageCode = ImageCodeUtil.getSecurityCode();
        String codeId = UUID.randomUUID().toString();
        //存储验证码随机数到redis
        //设置key的存活时间为15分钟
        stringRedisTemplate.opsForValue().set(codeId,imageCode,15, TimeUnit.MINUTES);

        HashMap<String,Object> map=new HashMap<>();

        //生成验证码图片，转为Base64
        try {
            String base64Img = ImageCodeUtil.careateImgBase64(imageCode);
            map.put("imageCode",base64Img);
            map.put("codeId",codeId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public HashMap<String, Object> login(AdminDTO adminDTO) {
        //获取验证码
        //验证
        ValueOperations<String, String> stringOption = stringRedisTemplate.opsForValue();
        String codeId = stringOption.get(adminDTO.getCodeId());
        HashMap<String, Object> map = new HashMap<>();
        if (codeId != null) {
            if (codeId.equals(adminDTO.getEnCode())) {
                Admin admin = adminDao.selectByName(adminDTO.getUsername());
                if (admin != null) {
                    if (adminDTO.getPassword().equals(admin.getPassword())) {
                        //存储用户标记
                        String adminUUID = UUIDUtil.getUUID();
                        stringOption.set(adminUUID, admin.getId().toString(), 1, TimeUnit.DAYS);
                        map.put("status", 200);
                        map.put("message", adminUUID);
                    } else {
                        map.put("status", 401);
                        map.put("message", "密码错误");
                    }
                } else {
                    map.put("status", 401);
                    map.put("message", "该用户不存在");
                }
            } else {
                map.put("status", 401);
                map.put("message", "验证码不正确");
            }
        }else{
                map.put("status", 401);
                map.put("message", "验证码不能为空");
            }
            return map;
    }

    @Override
    public Admin queryToken( String token) {
        //获取用户id
        String adminIds = stringRedisTemplate.opsForValue().get(token);
        //根据id查询用户信息
      //  System.out.println(adminDao.selectById(Integer.valueOf(adminId)));
        Integer adminId=Integer.valueOf(adminIds);
       // System.out.println(adminId);
        Admin admin = adminDao.selectById((Integer) adminId);
        System.out.println(admin);
        return admin;

    }

    @Override
    public HashMap<String, Object> logout(String token) {
        //获取用户id
         stringRedisTemplate.delete(token);
        HashMap<String,Object>map=new HashMap<>();
        map.put("massage","删除成功");
        return map;
    }
}
