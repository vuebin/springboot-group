package com.vuebin.jpa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.vuebin.common.core.service.RedisService;
import com.vuebin.common.core.util.HttpContextUtil;
import com.vuebin.common.core.util.ValidateUtil;
import com.vuebin.common.model.constants.RedisConstant;
import com.vuebin.common.model.dto.LoginUser;
import com.vuebin.common.model.exception.AuthException;
import com.vuebin.jpa.service.AuthService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 鉴权相关服务实现类
 *
 * @author fengjiabin
 * @date 2019/6/12 15:25
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String getTokenByRequest() {

        HttpServletRequest request = HttpContextUtil.getRequest();

        String token = request.getHeader("token");

        if (ValidateUtil.isEmpty(token)) {
            throw new AuthException();
        }

        return token;
    }

    @Override
    public LoginUser getLoginUserByContext() {

        String token = getTokenByRequest();

        String userInfoKey = RedisConstant.LOGIN_USER_INFO_TOKEN + token;

        LoginUser loginUser = JSONObject.parseObject((String) RedisService.get(userInfoKey), new TypeReference<LoginUser>() {
        });

        if (ValidateUtil.isEmpty(loginUser)) {
            throw new AuthException();
        }

        return loginUser;
    }

}
