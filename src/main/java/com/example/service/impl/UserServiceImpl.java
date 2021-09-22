package com.example.service.impl;

import com.example.dao.UserMapper;
import com.example.dto.PageDTO;
import com.example.entity.User;
import com.example.entity.UserExample;
import com.example.service.UserService;
import com.example.vo.CommonResult;
import com.example.vo.CommonResultVO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public CommonResultVO queryAllPage(PageDTO pageDTO) {
        //创建条件对象
        UserExample example = new UserExample();
        //删除时间为空
        example.createCriteria().andDeleteTimeIsNull();
        //总数
        int total = userMapper.selectCountByExample(example);
        //创建分页对象
        RowBounds rowBounds = new RowBounds((pageDTO.getPage()-1)*pageDTO.getPageSize(),pageDTO.getPageSize());
        //查
        List<User> users = userMapper.selectByExampleAndRowBounds(example, rowBounds);
        //遍历集合
        for (User user : users) {
            //获取用户id
            Integer id = user.getId();
            //根据用户id查询学分
            user.setScore(88);
        }
      return new CommonResultVO(pageDTO.getPage(),total,users);



    }

    @Override
    public CommonResult update(User user) {
        try {
            //根据主键修改
            userMapper.updateByPrimaryKeySelective(user);
            return CommonResult.success();
        }catch (Exception e){
            // e.printStackTrace();
            return CommonResult.filed();
        }
    }

    @Override
    public User queryById(Integer id) {
        return  userMapper.selectByPrimaryKey(id);
    }

    @Override
    public CommonResult add(User user) {
        user.setUpdateTime(user.getCreateTime());
        try {
           userMapper.insertSelective(user);
            return CommonResult.success();
        }catch (Exception e){
            // e.printStackTrace();
            return CommonResult.filed();
        }
    }

    @Override
    public void delete(User user){
        user.setDeleteTime(new Date());
//        try {
            userMapper.updateByPrimaryKeySelective(user);
           // return CommonResult.success();
//        }catch (Exception e){
//            e.printStackTrace();
//            return CommonResult.filed();
//        }

    }
}
