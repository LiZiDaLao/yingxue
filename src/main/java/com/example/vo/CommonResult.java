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
       return new CommonResult("执行成功",200);
    }
    public static CommonResult filed(){
        return new CommonResult("执行失败",500);
    }
}
