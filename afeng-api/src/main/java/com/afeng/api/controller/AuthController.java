package com.afeng.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:授权相关接口
 * @Author hugf
 * @Date 2019/3/9
 * @Time 15:32
 */
@Api(description = "auth-api")
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * @param userName
     * @param password
     * @Description: 登录
     * @Author: WangChangJing
     * @Date: 2018/10/24
     */
    @ApiOperation(value="登陆")
    @PostMapping("/login")
    public void login(String userName,String password) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        logger.info("登录的用户名：{},加密后的密码：{}", userName, password);
        currentUser.login(token);
    }
    /**
     * @Description: 注销
     * @Author: WangChangJing
     * @Date: 2018/10/24
     */
    @ApiOperation(value="注销")
    @PostMapping("/logout")
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "hello";
    }

    @GetMapping(value = {"/unauthorized"})
    @ApiOperation(value = "未授权跳转接口")
    public String hello() {
        logger.info("unauthorized!!!");
        logger.debug("unauthorized!!!");
        logger.error("unauthorized!!!");
        return "unauthorized 哈哈哈";
    }

}
