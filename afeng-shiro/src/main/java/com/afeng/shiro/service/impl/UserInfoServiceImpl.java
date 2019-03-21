package com.afeng.shiro.service.impl;

import com.afeng.shiro.dao.UserInfoDao;
import com.afeng.shiro.domain.UserInfo;
import com.afeng.shiro.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hugf
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        logger.info("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}