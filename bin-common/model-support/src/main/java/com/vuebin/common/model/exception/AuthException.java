package com.vuebin.common.model.exception;

import lombok.Data;

/**
 * @author fengjiabin
 * @date 2019/6/12 15:02
 */
@Data
public class AuthException extends ServiceException {

    public AuthException() {
        super(600, "Token失效, 请重新登录");
    }

    public AuthException(String errorMessage) {
        super(600, errorMessage);
    }

    /**
     * 不拷贝栈信息，提高性能
     *
     * @author fengshuonan
     * @Date 2018/7/25 下午1:48
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }

}
