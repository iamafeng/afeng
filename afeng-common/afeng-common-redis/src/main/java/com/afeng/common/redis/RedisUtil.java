package com.afeng.common.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/**
 * @Description: redis封装工具类
 * @Author hugf
 * @Date 2019/3/14
 * @Time 11:18
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    RedisKeyPrefix redisKeyPrefix;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final Integer SUCCESS_CODE = 1;
    private static final Integer ERROR_CODE = 0;


    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取字符串型值
     *
     * @param key
     * @return
     */
    public String getStr(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }


    /**
     * 该方法是原子的，对key设置newValue这个值，并且返回key原来的旧值。
     * 假设key原来是不存在的，则会返回set的newValue
     *
     * @param key
     * @param value
     * @return
     */
    public String getStrAndSet(String key, String value) {
        return (String) redisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 获取整数
     *
     * @param key
     * @return
     */
    public Integer getInteger(String key) {
        return (Integer) redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置缓存
     *
     * @param key  key
     * @param o    值
     * @param time 过期时间(分钟)
     */
    public void set(String key, Object o, Long time) {
        if (o instanceof String) {

            if (time == null) {
                redisTemplate.opsForValue().set(key, o);
            } else {
                redisTemplate.opsForValue().set(key, o, time, TimeUnit.MINUTES);
            }

        } else if (o instanceof Map) {

            redisTemplate.opsForHash().putAll(key, (Map) o);

            if (time != null) {
                redisTemplate.expire(key, time, TimeUnit.MINUTES);
            }

        } else if (o instanceof List) {

            redisTemplate.opsForList().rightPushAll(key, (List) o);

            if (time != null) {
                redisTemplate.expire(key, time, TimeUnit.MINUTES);
            }

        } else if (o instanceof Set) {

            redisTemplate.opsForSet().add(key, o);
            if (time != null) {
                redisTemplate.expire(key, time, TimeUnit.MINUTES);
            }

        } else {

            if (time == null) {
                redisTemplate.opsForValue().set(key, o);
            } else {
                redisTemplate.opsForValue().set(key, o, time, TimeUnit.MINUTES);
            }
        }
    }
    /**
     * 设置缓存
     *
     * @param key  key
     * @param o    值
     * @param cachedTypeEnum 过期时间
     */
    public void set(String key, Object o, CachedTypeEnum cachedTypeEnum) {
        if (o instanceof String) {

            if (cachedTypeEnum == null) {
                redisTemplate.opsForValue().set(key, o);
            } else {
                redisTemplate.opsForValue().set(key, o, cachedTypeEnum.getTimeout(),cachedTypeEnum.getUnit());
            }

        } else if (o instanceof Map) {

            redisTemplate.opsForHash().putAll(key, (Map) o);

            if (cachedTypeEnum != null) {
                redisTemplate.expire(key, cachedTypeEnum.getTimeout(), cachedTypeEnum.getUnit());
            }

        } else if (o instanceof List) {

            redisTemplate.opsForList().rightPushAll(key, (List) o);

            if (cachedTypeEnum != null) {
                redisTemplate.expire(key, cachedTypeEnum.getTimeout(), cachedTypeEnum.getUnit());
            }

        } else if (o instanceof Set) {

            redisTemplate.opsForSet().add(key, o);
            if (cachedTypeEnum != null) {
                redisTemplate.expire(key, cachedTypeEnum.getTimeout(), cachedTypeEnum.getUnit());
            }

        } else {

            if (cachedTypeEnum == null) {
                redisTemplate.opsForValue().set(key, o);
            } else {
                redisTemplate.opsForValue().set(key, o, cachedTypeEnum.getTimeout(), cachedTypeEnum.getUnit());
            }
        }
    }

    /**
     * 获取map的值
     *
     * @param key   key
     * @param field map的key
     * @return
     */
    public Object getMapValue(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取整个map
     *
     * @param key key
     * @return
     */
    public Map getMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 设置map的某个key的值
     *
     * @param key        redis key
     * @param fieldName  map对应的key
     * @param fieldValue map对应的value
     */
    public void setMapValue(String key, String fieldName, Object fieldValue) {
        redisTemplate.opsForHash().put(key, fieldName, fieldValue);
    }

    public void lpush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    public Object lpop(String key, String value) {
        return redisTemplate.opsForList().leftPop(key);
    }

    public void rpush(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    public Object rpop(String key, String value) {
        return redisTemplate.opsForList().rightPop(key);
    }

    public List getList(String key) {
        long length = redisTemplate.opsForList().size(key);
        return redisTemplate.opsForList().range(key, 0, length);
    }

    /**
     * 获取缓存对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getObj(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 批量获取对象
     *
     * @param keys
     * @param <T>
     * @return
     */
    public <T> List<T> getList(final Set<String> keys) {
        final RedisSerializer<T> valueSerializer = redisTemplate.getValueSerializer();
        final RedisSerializer keySerializer = redisTemplate.getKeySerializer();
        return redisTemplate.executePipelined(new RedisCallback<List<T>>() {
            @Override
            public List<T> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Iterator iterator = keys.iterator();
                while (iterator.hasNext()) {
                    valueSerializer.deserialize(redisConnection.get(keySerializer.serialize(iterator.next())));
                }
                return null;
            }
        });
    }

    /**
     * 根据正则表达式获取key
     *
     * @param str
     * @return
     */
    public Set<String> keys(final String str) {
        Set<String> result = (Set<String>) redisTemplate.execute(new RedisCallback<Set<String>>() {
            @Override
            public Set<String> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Set<byte[]> temp = redisConnection.keys(str.getBytes());
                Set<String> sets = new HashSet<String>();
                Iterator<byte[]> iter = temp.iterator();
                while (iter.hasNext()) {
                    sets.add(new String(iter.next()));
                }
                return sets;
            }
        });
        return result;
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量删除缓存
     *
     * @param key
     */
    public void delete(Collection<String> key) {
        redisTemplate.delete(key);
    }

    /**
     * 在key对应的值上面增加value
     *
     * @param key
     * @param value
     * @return
     */
    public Long increment(String key, long value) {
        return redisTemplate.opsForValue().increment(key, value);
    }

    /**
     * 在field对应的值上面增加value
     *
     * @param key
     * @param value
     * @return
     */
    public Long incrementMapKey(String key, String field, long value) {
        return redisTemplate.opsForHash().increment(key, field, value);
    }

    public List<?> multiGet(List<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    public void multiSet(Map<String, String> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Integer setNx(final String lockNm, final String value, final long expire) {

        logger.info("setNx start,lockNm:" + lockNm + ",value:" + value + ",expire:" + expire);

        RedisSerializer serializer = redisTemplate.getStringSerializer();
        byte[] keyBytes = serializer.serialize(lockNm);
        byte[] valBytes = serializer.serialize(value);

        RedisConnection conn = redisTemplate.getConnectionFactory().getConnection();
        // boolean locked = conn.setNX(keyBytes, value.getBytes());
        boolean locked = conn.setNX(keyBytes, valBytes);
        logger.info("setNx end,lockNm:" + lockNm + ",locked:" + locked);
        if (locked) {
            if (expire > 0) {
                conn.expire(keyBytes, expire);
                conn.close();
            }
            return SUCCESS_CODE;
        }
        conn.close();
        return ERROR_CODE;
    }

    @SuppressWarnings("unchecked")
    public String get(final String key) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] data = connection.get(redisTemplate.getStringSerializer().serialize(key));
                    connection.close();
                    if (null == data) {
                        return null;
                    }
                    return redisTemplate.getStringSerializer().deserialize(data);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("redis get val error, key : " + key);
        }
        String rtnStr = null;
        if (null != obj) {
            rtnStr = obj.toString();
        }

        logger.info("redis get val by key:" + key + " ,val:" + rtnStr);
        return rtnStr;
    }

    @SuppressWarnings("unchecked")
    public boolean setNx(final String lockNm, final String value) {
        logger.info("setNx start,lockNm:{},value:{}", lockNm, value);
        boolean setFlag = redisTemplate.opsForValue().setIfAbsent(lockNm, value);
        logger.info("setNx end,lockNm:{},setFlag:{}", lockNm, setFlag);
        return setFlag;
    }

    @SuppressWarnings("unchecked")
    public boolean setIfAbsent(final String lockNm, final String value, final long expire) {
        boolean setFlag = setNx(lockNm, value);
        if (setFlag && expire > 0) {
            logger.info("setIfAbsent setExpire,lockNm:{},expire:{}", lockNm, expire);
            redisTemplate.expire(lockNm, expire, TimeUnit.SECONDS);
        }
        return setFlag;
    }

    @SuppressWarnings("unchecked")
    public boolean checkExpireBeforeSetIfAbsent(final String lockNm, final String value, final long expire) {
        if (isOverTime(lockNm, expire)) {
            logger.info("key:{}已超时,执行删除操作", lockNm);
            redisTemplate.delete(lockNm);
        } else {
            //有没超时则直接返回，不必再多set一次
            return true;
        }
        return setIfAbsent(lockNm, value, expire);
    }

    /**
     * 检查重复
     *
     * @param code
     * @param value
     */
    public int checkRepeat(String code, String value, long milliseconds) {
        int rtn = 0;
        String val = get(code);
        if (StringUtils.isBlank(val)) {
            rtn = setNx(code, value, milliseconds);
        }
        return rtn;
    }

    /**
     * 获取有效时间
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 设置有效时间
     *
     * @param key
     * @param time
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean setExpire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 有效时间是否超时指定时间 传参timeout=0或不存在key或设置永久则默认超时
     *
     * @param key
     * @param timeout
     * @return
     */
    public boolean isOverTime(String key, long timeout) {
        if (timeout <= 0) {
            return true;
        }
        long expireTime = getExpire(key);
        // -1:设置有效时间永久,-2:不存在key
        if (expireTime < 0) {
            return true;
        } else {
            return expireTime > timeout;
        }
    }

    public void setObj(String key, Object obj) {
        redisTemplate.opsForValue().set(key, obj);
    }

    /**
     * 从hash中get
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T get4Hash(String hashId, String key) {
        return (T) redisTemplate.opsForHash().get(hashId, key);
    }

    /**
     * 存入hash
     *
     * @param hashId
     * @param key
     * @param val
     */
    @SuppressWarnings("unchecked")
    public int put2Hash(String hashId, String key, Object val) {
        redisTemplate.opsForHash().put(hashId, key, val);
        return SUCCESS_CODE;
    }

    /**
     * 从hash中get sysConf
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getConf4Hash(String key) {
        return (T) redisTemplate.opsForHash().get(redisKeyPrefix.SYS_CONF, key);
    }

    /**
     * 存入sysConf hash
     *
     * @param key
     * @param val
     */
    @SuppressWarnings("unchecked")
    public int putConf2Hash(String key, Object val) {
        redisTemplate.opsForHash().put(redisKeyPrefix.SYS_CONF, key, val);
        return SUCCESS_CODE;
    }

}
