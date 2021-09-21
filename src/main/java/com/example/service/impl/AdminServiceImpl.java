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
        HashMap<String,Object> map=new HashMap<>();
        if(codeId!=null){
        if(codeId.equals(adminDTO.getEnCode())){
            Admin admin = adminDao.selectByName(adminDTO.getUsername());
            if(admin!=null){
                if(adminDTO.getPassword().equals(admin.getPassword())){
                    String adminUUID = UUIDUtil.getUUID();
                    stringOption.set(adminUUID,admin.getId().toString(),1,TimeUnit.DAYS);
                    map.put("status",200);
                    map.put("massage",admin);
                }else {
                    map.put("status",401);
                    map.put("massage","该用户不存在");
                }
            }else {
                map.put("status",401);
                map.put("massage","该用户不存在");
            }
        }
        }else {
            map.put("status",401);
            map.put("massage","该用户不存在");
        }
        return map;
    }
}
