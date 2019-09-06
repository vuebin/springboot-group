package com.vuebin.common.model.exception.enums;

import com.vuebin.common.model.exception.AbstractBaseExceptionEnum;

/**
 * core模块的异常集合
 *
 * @author fengjiabin
 * @date 2019/6/12 9:09
 */
public enum CoreExceptionEnum implements AbstractBaseExceptionEnum {

    TOKEN_INVALID(600, "Token失效, 请重新登录"),
    NO_CURRENT_USER(700, "当前没有登录的用户"),
    SERVICE_ERROR(500, "服务器异常");

    CoreExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
