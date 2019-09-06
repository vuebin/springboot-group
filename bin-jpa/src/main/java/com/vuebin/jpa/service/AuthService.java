package com.vuebin.jpa.service;

import com.vuebin.common.model.dto.LoginUser;

/**
 * 鉴权相关服务层
 *
 * @author fengjiabin
 * @date 2019/6/12 15:25
 */
public interface AuthService {

    /**
     * 从请求中获取Token
     *
     * @return
     */
    String getTokenByRequest();

    /**
     * 通过上下文获取用户登录信息
     *
     * @return
     */
    LoginUser getLoginUserByContext();

}
