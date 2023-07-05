package io.ispacc.orion.admin.core.common;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 17:06
 */
@RestControllerAdvice
@Log4j2
public class BaseExceptionHandler {
    @ExceptionHandler({OrionException.class})
    public CommonResult<?> handlerOrionException(OrionException e) {
        if (StringUtils.isNotEmpty(e.getMessage())) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.failed(e.getErrorCode());
    }
}
