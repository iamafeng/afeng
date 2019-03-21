package com.afeng.api.redis;

import com.afeng.common.redis.CachedTypeEnum;
import com.afeng.common.redis.RedisKeyPrefix;
import com.afeng.common.redis.RedisUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author hugf
 * @Date 2019/3/14
 * @Time 10:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisKeyPrefix redisKeyPrefix;

    @Before
    public void testSet() {
        String key = redisKeyPrefix.getKey(redisKeyPrefix.USER_NAME, 2);
        String user = "I am afeng!!!"+CachedTypeEnum.MINUTE_2.getTimeout() + ":" +CachedTypeEnum.MINUTE_2.getUnit().toString();
        redisUtil.set(key, user, CachedTypeEnum.MINUTE_2);
    }

    @Test
    public void testGet() {
        String key = redisKeyPrefix.getKey(redisKeyPrefix.USER_NAME, 2);
        String str = redisUtil.get(key);
        System.out.println(str);
    }


}
