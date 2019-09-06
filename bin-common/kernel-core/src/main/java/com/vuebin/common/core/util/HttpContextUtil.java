package com.vuebin.common.core.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 快捷获取HttpServletRequest,HttpServletResponse
 *
 * @author fengjiabin
 * @date 2019/6/12 9:39
 */
public class HttpContextUtil {

    /**
     * 获取请求的ip地址
     *
     * @return
     */
    public static String getIp() {
        HttpServletRequest request = HttpContextUtil.getRequest();
        if (request == null) {
            return "127.0.0.1";
        } else {
            return request.getRemoteHost();
        }
    }

    /**
     * 获取当前请求的Request对象
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            return requestAttributes.getRequest();
        }
    }

    /**
     * 获取当前请求的Response对象
     *
     * @return
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            return requestAttributes.getResponse();
        }
    }

    /**
     * 获取session对象
     *
     * @return
     */
    public static HttpSession getSession() {
        HttpServletRequest request = HttpContextUtil.getRequest();
        HttpSession session = request.getSession();
        return session;
    }


}
