package com.example.service.impl;

import com.example.service.AdminService;
import com.example.util.ImageCodeUtil;
import com.example.util.UUIDUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
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
}
