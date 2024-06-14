package com.neusoft.neu6053.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class HttpResponseEntity {
    //状态码 200成功 500失败
    private String code;
    //数据
    private Object data;
    //提示信息
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HttpResponseEntity{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }


    public static HttpResponseEntity success(Object data) {
        HttpResponseEntity response = new HttpResponseEntity();
        response.setCode("200");
        response.setData(data);
        response.setMessage("Success");
        return response;
    }


    public static HttpResponseEntity success(Object data, String message) {
        HttpResponseEntity response = new HttpResponseEntity();
        response.setCode("200");
        response.setData(data);
        response.setMessage(message);
        return response;
    }

    public static HttpResponseEntity failure(String message) {
        HttpResponseEntity response = new HttpResponseEntity();
        response.setCode("500");
        response.setMessage(message);
        return response;
    }

}
