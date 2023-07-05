package io.ispacc.orion.admin.core.common;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 入参校验异常统一返回
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult<String> validException(MethodArgumentNotValidException e) {
        String msg = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return CommonResult.failed(msg);
    }
}
