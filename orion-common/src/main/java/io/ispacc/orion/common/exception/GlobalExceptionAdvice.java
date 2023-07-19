package io.ispacc.orion.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import io.ispacc.orion.common.api.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 运行时异常返回
     */
    @ExceptionHandler({OrionException.class})
    public CommonResult<?> handlerOrionException(OrionException e) {
        if (StringUtils.isNotEmpty(e.getMessage())) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.failed(e.getErrorCode());
    }

    /**
     * 入参校验异常统一返回
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult<String> validException(MethodArgumentNotValidException e) {
        String msg = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return CommonResult.failed(msg);
    }

    /**
     * 验证登录状态
     */
    @ExceptionHandler(NotLoginException.class)
    public CommonResult<String> notLoginException(NotLoginException e) {
        String msg = Objects.requireNonNull(e.getMessage());
        return CommonResult.failed(msg);
    }
}
