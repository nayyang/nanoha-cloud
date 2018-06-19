/**  
 *RedisGeneratorDao.java         2016-9-21上午10:25:13
 *@Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 *@Company:http://www.vivo.com.cn/
 * 
 */
package redis.base;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Title:持久化操作
 * @Description:
 * @Author:nanoha
 * @Since:2016-9-21
 * @Version:1.0
 */
public abstract class RedisGeneratorDao<K extends Serializable, V extends Serializable> {
    @Autowired
    protected RedisTemplate<K, V> redisTemplate;

    protected RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }
}
