package com.example.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.JsonObject;

import java.util.Random;

public class SendSmsUtil {

    //配置阿里云的密钥
    private static final String accessKeyId="LTAI5tJSuNpQAJUvzztwDyqS";
    private static final String accessSecret="rYNXKLAym8C4xdxx4UxIlkpoTmS8Gn";

    public static CommonResponse sendPhoneCode(String phoneNumbers,String code){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);//自己账号的AccessKey信息
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");//短信服务的服务接入地址
        request.setSysVersion("2017-05-25");//API的版本号
        request.setSysAction("SendSms");//API的名称
        //19977958589,15340328925,15918962252
        request.putQueryParameter("PhoneNumbers", phoneNumbers);//接收短信的手机号码
        request.putQueryParameter("SignName", "小蛋黄");//短信签名名称
        request.putQueryParameter("TemplateCode", "SMS_172008044");//短信模板ID
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");//短信模板变量对应的实际值
        try {
            CommonResponse response = client.getCommonResponse(request);
            //System.out.println(response.getData());

            return response;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return null;
    }

    //获取随机数  参数：随机数的位数
    public static  String  getRandom(int n){
        char[] code =  "0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(code[new Random().nextInt(code.length)]);
        }
        return sb.toString();
    }

    public static String sendPhone(String phoneNumber,String code){
        //发送验证码
        CommonResponse commonResponse = sendPhoneCode(phoneNumber, code);
        //获取返回数据  {"RequestId":"A4D75317-60FE-507F-9AA3-EF9668A5B6E2","Message":"OK","BizId":"260508032301736924^0","Code":"OK"}
        String data = commonResponse.getData();

        //将json字符串转为java对象
        JSONObject jsonObject = JSON.parseObject(data);

        //获取值
        String codes = (String) jsonObject.get("Code");

        String message=null;
        if(codes.equals("OK"))message="发送成功";
        if(codes.equals("isv.MOBILE_NUMBER_ILLEGAL"))message="非法的手机号";
        if(codes.equals("isv.AMOUNT_NOT_ENOUGH"))message="账户余额不足";

        return message;
    }

    public static void main(String[] args) {

        String s = sendPhone("15926636797", "6789");
        System.out.println(s);
    }
}