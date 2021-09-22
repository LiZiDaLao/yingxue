package com.example.service;

import com.example.dto.FeedbackDTO;
import com.example.entity.Feedback;
import com.example.vo.CommonResult;
import com.example.vo.FeedbackVO;
import org.springframework.web.bind.annotation.RequestBody;

public interface FeedbackService {
    FeedbackVO queryAllPage( FeedbackDTO feedbackDTO);
    //删除
    CommonResult delete(Feedback feedback);
}
