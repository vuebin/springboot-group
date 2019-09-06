package com.vuebin.jpa.service.impl;

import com.vuebin.common.core.annotation.ParamValidator;
import com.vuebin.common.core.service.RedisService;
import com.vuebin.common.core.util.JwtTokenUtil;
import com.vuebin.common.model.constants.RedisConstant;
import com.vuebin.common.model.dto.LoginUser;
import com.vuebin.common.model.entity.User;
import com.vuebin.common.model.system.ResponseData;
import com.vuebin.jpa.repository.UserRepository;
import com.vuebin.jpa.service.AuthService;
import com.vuebin.jpa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author fengjiabin
 * @date 2019/6/10 20:57
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UserRepository userRepository;

    @Resource
    private AuthService authService;

    @Override
    @ParamValidator
    public ResponseData saveUser(User user) {

        userRepository.saveAndFlush(user);

        log.info("bin ->【Jpa】添加user对象成功...");

        return ResponseData.success();
    }

    @Override
    public ResponseData login(String name, Integer age) {

        User user = userRepository.findByName(name);

        if (Objects.isNull(user)) {
            return ResponseData.error(-1, "该用户不存在");
        }

        if (!user.getAge().equals(age)) {
            return ResponseData.error(-2, "该用户密码不正确");
        }

//        生成token
        String token = jwtTokenUtil.generateToken(user.getName(), null);

//        存入redis(用户信息 + token)
        LoginUser loginUser = new LoginUser();
        loginUser.setToken(token);
        loginUser.setUser(user);

//        用户登录信息(LoginUser => User + Token)
        String userInfoKey = RedisConstant.LOGIN_USER_INFO_TOKEN + token;
        RedisService.put(userInfoKey, loginUser, 3L, TimeUnit.MINUTES);

        return ResponseData.success(token);
    }

    @Override
    public ResponseData listUser() {

        List<User> list = userRepository.findAll();

        return ResponseData.success(list);
    }

    @Override
    public ResponseData getUserInfo() {

        LoginUser loginUser = authService.getLoginUserByContext();

        return ResponseData.success(loginUser);
    }

}
