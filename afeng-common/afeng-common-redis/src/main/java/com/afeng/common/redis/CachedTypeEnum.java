package com.afeng.common.redis;

import java.util.concurrent.TimeUnit;


/**
 * @Description: 缓存有效时间
 * @Author hugf
 * @Date 2019/3/14
 * @Time 11:18
 */
public enum CachedTypeEnum {
    /**
    * 天
    * */
    DAY(1, TimeUnit.DAYS),
    WEEK(7, TimeUnit.DAYS),
    HALF_MONTH(15, TimeUnit.DAYS),
    DAYS_28(28, TimeUnit.DAYS),
    DAYS_29(29, TimeUnit.DAYS),
    DAYS_30(30, TimeUnit.DAYS),
    DAYS_31(31, TimeUnit.DAYS),
    /**
     * 时
     * */
    HOURS_2(2, TimeUnit.HOURS),
    HOURS_12(12, TimeUnit.HOURS),
    HOURS_24(24, TimeUnit.HOURS),
    /**
     * 分
     * */
    MINUTE_1(1, TimeUnit.MINUTES),
    MINUTE_2(2, TimeUnit.MINUTES),
    MINUTE_5(5, TimeUnit.MINUTES),
    MINUTE_10(10, TimeUnit.MINUTES),
    MINUTE_20(20, TimeUnit.MINUTES),
    /**
     * 秒
     * */
    SECONDS_5(5, TimeUnit.SECONDS),
    SECONDS_10(10, TimeUnit.SECONDS)
    ;


    private CachedTypeEnum(long timeout, TimeUnit unit) {
        this.timeout = timeout;
        this.unit = unit;
    }

    private long timeout;
    private TimeUnit unit;

    public long getTimeout() {
        return timeout;
    }

    public TimeUnit getUnit() {
        return unit;
    }

}
