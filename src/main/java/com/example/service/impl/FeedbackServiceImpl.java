package com.example.service.impl;

import com.example.dao.FeedbackMapper;
import com.example.dto.PageDTO;
import com.example.entity.Feedback;
import com.example.entity.FeedbackExample;
import com.example.service.FeedbackService;
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
public class FeedbackServiceImpl implements FeedbackService {
    @Resource
    FeedbackMapper feedbackMapper;
    @Override
    public CommonResultVO queryAllPage(PageDTO pageDTO) {
        //创建条件对象
        FeedbackExample feedbackExample = new FeedbackExample();
        //删除时间空
        feedbackExample.createCriteria().andDeleteTimeIsNull();
        //总数
        int total = feedbackMapper.selectCountByExample(feedbackExample);

        //创建分页对象
        RowBounds rowBounds = new RowBounds((pageDTO.getPage()-1)* pageDTO.getPageSize(), pageDTO.getPageSize());
        List<Feedback> feedbacks = feedbackMapper.selectByExampleAndRowBounds(feedbackExample, rowBounds);
        return new CommonResultVO(pageDTO.getPage(),total,feedbacks);

    }

    @Override
    public CommonResult delete(Feedback feedback) {
        feedback.setDeleteTime(new Date());
        try {
            feedbackMapper.updateByPrimaryKeySelective(feedback);
         return CommonResult.success();
        }catch (Exception e){
          // e.printStackTrace();
           return CommonResult.filed();
        }
    }
}
