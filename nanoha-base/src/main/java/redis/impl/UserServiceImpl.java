/**  
 *UserServiceImpl.java         2016-9-20下午4:28:30
 *@Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 *@Company:http://www.vivo.com.cn/
 * 
 */
package redis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import redis.UserService;
import redis.entity.User;

/**
 * @Title:
 * @Description:
 * @Author:nanoha
 * @Since:2016-9-20
 * @Version:1.0
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    /*
     * (non-Javadoc)
     * 
     * @see redis.UserService#add(redis.entity.User)
     */
    @Override
    public void add(User user) {
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(user.getId(), user);
    }

    /*
     * (non-Javadoc)
     * 
     * @see redis.UserService#getUser(java.lang.String)
     */
    @Override
    public User getUser(String key) {
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        User user = valueOperations.get(key);
        return user;
    }

}
