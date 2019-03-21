package com.afeng.common.redis;

import org.springframework.stereotype.Component;

/**
 * @Description:系统－redis key常量
 * @Author hugf
 * @Date 2019/3/14
 * @Time 10:21
 */
@Component
public class RedisKeyPrefix {
    /**
     * 获取redis key
     * @param keyPrefix redis前缀
     * @param o key变量
     * @return
     */
    public String getKey(String keyPrefix, Object o){
        return String.format("%s%s", keyPrefix, o);
    }

    public String SYS_CONF = "sys_conf";
    public String USER_INFO = "user:info";
    public String USER_NAME = "user:name:";
}
