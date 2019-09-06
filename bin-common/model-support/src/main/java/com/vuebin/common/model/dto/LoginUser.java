package com.vuebin.common.model.dto;

import com.vuebin.common.model.entity.User;
import lombok.Data;

/**
 * 当前用户的登录信息
 *
 * @author fengjiabin
 * @date 2019/6/12 15:20
 */
@Data
public class LoginUser {

    /**
     * 用户基本信息
     */
    private User user;

    /**
     * 用户登录成功颁发的token
     */
    private String token;

}
