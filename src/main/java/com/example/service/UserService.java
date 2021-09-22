package com.example.service;

import com.example.dto.PageDTO;
import com.example.entity.User;
import com.example.vo.CommonResult;
import com.example.vo.CommonResultVO;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    //查询用户信息
    CommonResultVO queryAllPage( PageDTO pageDTO);
    //修改状态
    CommonResult update(User user);
    //根据id查
    User queryById(Integer id);
    //添加用户
    CommonResult add(User user);
    //删除
    void delete(User user);

}
