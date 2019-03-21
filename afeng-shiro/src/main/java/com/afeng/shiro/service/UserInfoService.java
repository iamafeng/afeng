package com.afeng.shiro.service;

import com.afeng.shiro.domain.UserInfo;

/**
 * @author hugf
 */
public interface UserInfoService {
    /**
     * 通过username查找用户信息;
     */
    UserInfo findByUsername(String username);
}