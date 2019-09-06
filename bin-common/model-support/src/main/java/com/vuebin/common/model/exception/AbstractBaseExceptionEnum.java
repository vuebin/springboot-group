package com.vuebin.common.model.exception;

/**
 * 异常规范
 *
 * @author fengjiabin
 * @date 2019/6/11 22:14
 */
public interface AbstractBaseExceptionEnum {

    /**
     * 获取异常的状态码
     */
    Integer getCode();

    /**
     * 获取异常的提示信息
     */
    String getMessage();

}
