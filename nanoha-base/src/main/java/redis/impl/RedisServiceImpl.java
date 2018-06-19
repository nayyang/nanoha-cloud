/**  
 *RedisServiceImpl.java         2016年9月20日上午11:07:46
 *@Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 *@Company:http://www.vivo.com.cn/
 * 
 */
package redis.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import redis.RedisService;

/**
 * @Title: 封装redis 缓存服务器服务接口
 * @Description:
 * @Author:nanoha
 * @Since:2016年9月20日
 * @Version:1.0
 */
@Service(value = "redisService")
public class RedisServiceImpl implements RedisService {

    private static Logger logger = LogManager.getLogger(RedisServiceImpl.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static String REDISCODE = "utf-8";
    private static Long NOLIVETIME = 0L;
    private static String SUCCESS = "SUCCESS";

    private RedisServiceImpl() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see redis.RedisService#trim(java.lang.String, long, int)
     */
    @Override
    public void trim(String key, long start, int end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    /*
     * (non-Javadoc)
     * 
     * @see redis.RedisService#set(java.lang.String, long, java.lang.String)
     */
    @Override
    public void set(String key, long index, String value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see redis.RedisService#index(java.lang.String, long)
     */
    @Override
    public String index(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see redis.RedisService#remove(java.lang.String, long, java.lang.String)
     */
    @Override
    public void remove(String key, long i, String value) {
        redisTemplate.opsForList().remove(key, i, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see redis.RedisService#range(java.lang.String, int, int)
     */
    @Override
    public List<String> range(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 
     * @Description: 出队
     * @param key
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016-9-21
     */
    @Override
    public String outQueue(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see redis.RedisService#inQueue(java.lang.String, java.lang.String)
     */
    @Override
    public Long inQueue(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see redis.RedisService#pop(java.lang.String)
     */
    @Override
    public String pop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see redis.RedisService#push(java.lang.String, java.lang.String)
     */
    @Override
    public Long push(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see redis.RedisService#del(java.lang.String)
     */
    @Override
    public long del(final String key) {

        return redisTemplate.execute(new RedisCallback<Long>() {

            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(key.getBytes());
            }
        });
    }

    /**
     * 
     * @Description: 添加key，value，设置存活时间
     * @param key
     * @param value
     * @param liveTime
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月20日
     */
    @Override
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback<Long>() {

            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

    /**
     * 
     * @Description: 添加key，value，设置存活时间
     * @param key
     * @param value
     * @param liveTime
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月20日
     */
    @Override
    public void set(String key, String value, long liveTime) {
        this.set(key.getBytes(), value.getBytes(), liveTime);
    }

    /**
     * 
     * @Description: 添加key，value
     * @param key
     * @param value
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月20日
     */
    @Override
    public void set(String key, String value) {
        this.set(key, value, NOLIVETIME);
    }

    /**
     * 
     * @Description: 序列化
     * @param key
     * @param value
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月20日
     */
    @Override
    public void set(byte[] key, byte[] value) {
        this.set(key, value, NOLIVETIME);
    }

    /**
     * 
     * @Description: 获取redis value (String)
     * @param key
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月20日
     */
    @Override
    public String get(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {

            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    return new String(connection.get(key.getBytes()), REDISCODE);
                } catch (Exception e) {
                    logger.error(e);
                }
                return "";
            }
        });
    }

    /**
     * 
     * @Description: 通过正则匹配keys
     * @param pattern
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月20日
     */
    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 
     * @Description: 检查key是否已经存在
     * @param key
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月20日
     */
    @Override
    public boolean exists(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }

    /**
     * 
     * @Description: 清空redis 所有数据
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月20日
     */
    @Override
    public String flushDB() {
        return redisTemplate.execute(new RedisCallback<String>() {

            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return SUCCESS;
            }
        });
    }

    /**
     * 
     * @Description: 查看redis里有多少数据
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月20日
     */
    @Override
    public long size() {
        return redisTemplate.execute(new RedisCallback<Long>() {

            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /**
     * 
     * @Description: 检查是否连接成功
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月20日
     */
    @Override
    public String ping() {
        return redisTemplate.execute(new RedisCallback<String>() {

            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see redis.RedisService#getRedisTemplate()
     */
    @Override
    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    @Override
    public void putHash(String key, Object field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);

    }

    @Override
    public Object getHash(String key, Object field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    @Override
    public Map entries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public Long deleteHash(String key, Object field) {
        return redisTemplate.opsForHash().delete(key, field);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<Map> forEntries(final List<String> keys) {
        return redisTemplate.execute(new RedisCallback<List<Map>>() {

            @Override
            public List<Map> doInRedis(RedisConnection connection) throws DataAccessException {
                List<Map> lm = new ArrayList<>();
                for (String key : keys) {
                    Map map = connection.hGetAll(key.getBytes());
                    lm.add(map);
                }
                return lm;
            }
        });
    }

    @Override
    public List<Map> forAllEntries(final List<String> keys) {
        return redisTemplate.execute(new RedisCallback<List<Map>>() {

            @Override
            public List<Map> doInRedis(RedisConnection connection) throws DataAccessException {
                List<Map> lm = new ArrayList<>();
                for (String key : keys) {
                    Map map = connection.hGetAll(key.getBytes());
                    lm.add(map);
                }
                return lm;
            }
        });
    }
}
