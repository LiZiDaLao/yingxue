package com.example.service;

import com.example.dto.PageDTO;
import com.example.entity.Feedback;
import com.example.vo.CommonResult;
import com.example.vo.CommonResultVO;

public interface FeedbackService {
    CommonResultVO queryAllPage(PageDTO pageDTO);
    //删除
    CommonResult delete(Feedback feedback);
}
