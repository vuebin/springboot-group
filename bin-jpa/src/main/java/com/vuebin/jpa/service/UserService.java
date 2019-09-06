package com.vuebin.jpa.service;

import com.vuebin.common.model.entity.User;
import com.vuebin.common.model.system.ResponseData;

/**
 * @author fengjiabin
 * @date 2019/6/10 20:56
 */
public interface UserService {

    /**
     * save user
     *
     * @param user
     * @return
     */
    ResponseData saveUser(User user);

    /**
     * login user
     *
     * @param age
     * @param name
     * @return
     */
    ResponseData login(String name, Integer age);

    /**
     * list user
     *
     * @return
     */
    ResponseData listUser();

    /**
     * get user info
     *
     * @return
     */
    ResponseData getUserInfo();

}
