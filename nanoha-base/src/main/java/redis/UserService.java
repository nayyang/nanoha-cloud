/**  
 *UserService.java         2016-9-20下午4:27:33
 *@Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 *@Company:http://www.vivo.com.cn/
 * 
 */
package redis;

import redis.entity.User;

/**
 * @Title:
 * @Description:
 * @Author:nanoha
 * @Since:2016-9-20
 * @Version:1.0
 */
public interface UserService {
    void add(User user);

    User getUser(String key);
}
