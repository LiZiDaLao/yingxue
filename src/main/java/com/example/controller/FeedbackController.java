package com.example.controller;

import com.example.dto.FeedbackDTO;
import com.example.entity.Feedback;
import com.example.entity.FeedbackExample;
import com.example.service.FeedbackService;
import com.example.vo.CommonResult;
import com.example.vo.FeedbackVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("feedback")
public class FeedbackController {
    @Resource
    FeedbackService feedbackService;
    @PostMapping("queryAllPage")
    public FeedbackVO queryAllPage(@RequestBody FeedbackDTO feedbackDTO){
        return feedbackService.queryAllPage(feedbackDTO);
    }
    @PostMapping("delete")
    public CommonResult delete(@RequestBody Feedback feedback){
        return null;
    }

}
