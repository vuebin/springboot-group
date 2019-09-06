package com.vuebin.common.model.system;

import lombok.Data;

import java.util.ArrayList;

/**
 * 通过返回结果对象
 *
 * @author fengjiabin
 * @date 2019/6/10 21:26
 */
@Data
public class ResponseData {

    private Boolean success;

    private Integer code;

    private String message;

    private Object data;

    public ResponseData(Boolean success, Integer code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 请求成功
     *
     * @param message
     * @param data
     * @return
     */
    public static ResponseData success(String message, Object data) {
        return new ResponseData(true, 0, message, data);
    }

    /**
     * 请求成功
     *
     * @param data
     * @return
     */
    public static ResponseData success(Object data) {
        return new ResponseData(true, 0, "success", data);
    }

    /**
     * 请求成功
     *
     * @return
     */
    public static ResponseData success() {
        return new ResponseData(true, 0, "success", new ArrayList<>());
    }

    /**
     * 请求失败
     *
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ResponseData error(Integer code, String message, Object data) {
        return new ResponseData(false, code, message, data);
    }

    /**
     * 请求失败
     *
     * @param code
     * @param message
     * @return
     */
    public static ResponseData error(Integer code, String message) {
        return new ResponseData(false, code, message, new ArrayList<>());
    }

}
