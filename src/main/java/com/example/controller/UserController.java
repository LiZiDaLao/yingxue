package com.example.controller;

import com.example.dao.UserMapper;
import com.example.dto.PageDTO;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.util.SendSmsUtil;
import com.example.vo.CommonResult;
import com.example.vo.CommonResultVO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    UserService userService;
    @PostMapping("queryAllPage")
    public CommonResultVO queryAllPage(@RequestBody PageDTO pageDTO){
        return userService.queryAllPage(pageDTO);
    }
    @PostMapping("update")
    public CommonResult update(@RequestBody User user){
        return userService.update(user);
    }
    @GetMapping("queryById")
    public User queryById(Integer id){
        return userService.queryById(id);
    }
    @Resource
    HttpServletRequest request;
    @PostMapping("uploadAvatarUrl")
    public String uploadAvatarUrl(MultipartFile avatarUrl){
        //1.获取文件名
        String filename = avatarUrl.getOriginalFilename();

        String newName=new Date().getTime()+"-"+filename;

        //2.获取文件路径  根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/avatar");

        //创建文件夹
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }

        //3.文件上传
        try {
            avatarUrl.transferTo(new File(realPath,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newName;
    }
    @PostMapping("add")
    public CommonResult add(@RequestBody User user){
        return userService.add(user);
    }
    @DeleteMapping("delete")
    public CommonResult delete(User user){

        System.out.println(user);
        try {
            userService.delete(user);
            return CommonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.filed();
        }
    }
    @GetMapping("getPhoneCode")
    public HashMap<String, Object> getPhoneCode(String phone){

        //1.随机数验证码
        String random = SendSmsUtil.getRandom(6);

        //2.使用redis存储随机数
        stringRedisTemplate.opsForValue().set(phone,random,2, TimeUnit.MINUTES);

        //3.发送手机验证码
        String message = SendSmsUtil.sendPhone(phone, random);

        HashMap<String, Object> map = new HashMap<>();
        map.put("message",message);

        if(message.equals("发送成功")){
            map.put("status",200);
        }else{
            map.put("status",500);
        }

        return map;
    }
@PostMapping("phoneCheck")
    public HashMap<String, Object> phoneCheck(String phone,String codes){
    String code = stringRedisTemplate.opsForValue().get(phone);
    HashMap<String, Object> map = new HashMap<>();
    if(code.equals(codes)){
      map.put("message","验证成功");
        map.put("status",200);

    }else {
        map.put("message","验证失败");
        map.put("status",500);
    }
    return map;
}

}
