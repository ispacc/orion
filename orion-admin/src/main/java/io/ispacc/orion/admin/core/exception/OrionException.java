package io.ispacc.orion.admin.core.exception;

import io.ispacc.orion.admin.core.common.IErrorCode;
import io.ispacc.orion.admin.core.common.ResultCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 16:52
 */

@Getter
@Setter
public class OrionException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private IErrorCode errorCode = ResultCode.FAILED;

    private String message;

    public OrionException() {
    }

    public OrionException(String message) {
        super(message);
        this.message = message;
    }

    public OrionException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
