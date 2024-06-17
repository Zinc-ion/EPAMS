package com.neusoft.neu6053.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

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

    //分页数据传回
    public static HttpResponseEntity success(IPage<?> data) {
        HttpResponseEntity response = new HttpResponseEntity();
        response.setCode("200");
        Map<String, Object> map = new HashMap<>();
        map.put("data", data.getRecords());
        map.put("totalRecords", data.getTotal());
        map.put("totalPages", data.getPages());
        map.put("currentPage", data.getCurrent());
        map.put("pageSize", data.getSize());
        response.setData(map);
        response.setMessage("Success");
        return response;
    }

    public static HttpResponseEntity failure(String message) {
        HttpResponseEntity response = new HttpResponseEntity();
        response.setCode("500");
        response.setMessage(message);
        return response;
    }

}
