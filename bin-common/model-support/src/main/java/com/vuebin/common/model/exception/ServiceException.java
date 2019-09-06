package com.vuebin.common.model.exception;

import lombok.Data;

/**
 * @author fengjiabin
 * @date 2019/6/11 22:14
 */
@Data
public class ServiceException extends RuntimeException {

    private Integer code;

    private String errorMessage;

    public ServiceException(Integer code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public ServiceException(AbstractBaseExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }

}
