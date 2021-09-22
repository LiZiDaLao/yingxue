package com.example.controller;

import com.example.dto.PageDTO;
import com.example.entity.Feedback;
import com.example.service.FeedbackService;
import com.example.vo.CommonResult;
import com.example.vo.CommonResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("feedback")
public class FeedbackController {
    @Resource
    FeedbackService feedbackService;
    @PostMapping("queryAllPage")
    public CommonResultVO queryAllPage(@RequestBody PageDTO pageDTO){
        return feedbackService.queryAllPage(pageDTO);
    }
    @PostMapping("delete")
    public CommonResult delete(@RequestBody Feedback feedback){
        return feedbackService.delete(feedback);

    }

}
