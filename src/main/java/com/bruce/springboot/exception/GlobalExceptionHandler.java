package com.bruce.springboot.exception;

import com.bruce.springboot.response.ApiResponse;
import com.chinacareer.ymtd.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.MessageFormat;
import java.util.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public Object handleService(BizException e) {
        log.error("Service exception: ", e);
        return ApiResponse.fail(e.getMessage(), e.getCode());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Object missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        String collect = MessageFormat.format("缺少参数:{0}", ex.getParameterName());
        return new ApiResponse<>(false, 1, "参数校验错误", collect);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object httpMessageNotReadableException() {
        return new ApiResponse<>(false, 1, "参数校验错误", "POST请求体丢失");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, List<String>> errorsMap = getBindingErrors(bindingResult);
        return new ApiResponse<>(false, 1, "参数校验错误", errorsMap);
    }

    /**
     * 错误信息收集
     */
    private Map<String, List<String>> getBindingErrors(BindingResult bindingResult) {
        Map<String, List<String>> errors = new HashMap<>();
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                List<String> fErrors = errors.computeIfAbsent(fieldError.getField(), k -> new ArrayList<>());
                fErrors.add(fieldError.getDefaultMessage());
            }
        }
        return errors;
    }
}
