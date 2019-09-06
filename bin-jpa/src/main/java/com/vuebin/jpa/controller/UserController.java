package com.vuebin.jpa.controller;

import com.vuebin.common.core.annotation.PassToken;
import com.vuebin.common.model.entity.User;
import com.vuebin.common.model.system.ResponseData;
import com.vuebin.jpa.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author fengjiabin
 * @date 2019/6/10 20:56
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public ResponseData saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PassToken
    @GetMapping("/login")
    public ResponseData login(@RequestParam String name, @RequestParam Integer age) {
        return userService.login(name, age);
    }

    @GetMapping("/list")
    public ResponseData listUser() {
        return userService.listUser();
    }

    @GetMapping("/info")
    public ResponseData getLoginUserByContext() {
        return userService.getUserInfo();
    }

}
