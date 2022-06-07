package com.bruce.springboot.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {
    private boolean success = true;
    private int code = 0;  // 0 成功, 1 失败 （保持和现有HR后台一致; 小程序暂时不读取code）
    private String message = "";
    private T data;

    public ApiResponse() {
        super();
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>();
    }

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> res = new ApiResponse<>();
        res.setData(data);
        return res;
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        ApiResponse<T> res = new ApiResponse<>();
        res.setMessage(message);
        res.setData(data);
        return res;
    }

    public static <T> ApiResponse<T> fail(String message) {
        ApiResponse<T> res = new ApiResponse<>();
        res.setSuccess(false);
        res.setCode(1);
        res.setMessage(message);
        return res;
    }

    public static <T> ApiResponse<T> fail(String message, int code) {
        ApiResponse<T> res = new ApiResponse<>();
        res.setSuccess(false);
        res.setCode(code);
        res.setMessage(message);
        return res;
    }
}
