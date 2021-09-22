package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult {
    private String message;
    private Integer status;
    public static CommonResult success(){
        CommonResult commonResult = new CommonResult();
        commonResult.setMessage("执行成功");
        commonResult.setStatus(200);
        return commonResult;
    }
    public static CommonResult filed(){
        CommonResult commonResult = new CommonResult();
        commonResult.setMessage("执行失败");
        commonResult.setStatus(500);
        return commonResult;
    }
}
