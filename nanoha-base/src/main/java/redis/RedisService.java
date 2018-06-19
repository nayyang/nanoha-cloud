/**  
 *RedisService.java         2016年9月20日上午10:51:27
 *@Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 *@Company:http://www.vivo.com.cn/
 * 
 */
package redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Title: RedisTemplate
 * @Description:
 * @Author:nanoha
 * @Since:2016年9月20日
 * @Version:1.0
 */
public interface RedisService {

    /**
     * 获取template
     * 
     * @Description:
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016-9-21
     */
    public RedisTemplate<String, String> getRedisTemplate();

    /**
     * 
     * @Description: 压栈
     * @param key
     * @param value
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016-9-21
     */
    Long push(String key, String value);

    /**
     * @Description: 通过key删除
     * @param keys
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月20日
     */
    long del(String key);

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
    void set(byte[] key, byte[] value, long liveTime);

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
    void set(String key, String value, long liveTime);

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
    void set(String key, String value);

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
    void set(byte[] key, byte[] value);

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
    String get(String key);

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
    Set<String> keys(String pattern);

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
    boolean exists(String key);

    /**
     * 
     * @Description: 清空redis 所有数据
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月20日
     */
    String flushDB();

    /**
     * 
     * @Description: 查看redis里有多少数据
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月20日
     */
    long size();

    /**
     * 
     * @Description: 检查是否连接成功
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月20日
     */
    String ping();

    /**
     * 剪切
     * 
     * @Description:
     * @param key
     * @param start
     * @param end
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016-9-21
     */
    void trim(String key, long start, int end);

    /**
     * 置值
     * 
     * @Description:
     * @param key
     * @param index
     * @param value
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016-9-21
     */
    void set(String key, long index, String value);

    /**
     * 检索
     * 
     * @Description:
     * @param key
     * @param index
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016-9-21
     */
    String index(String key, long index);

    /**
     * 移除
     * 
     * @Description:
     * @param key
     * @param i
     * @param value
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016-9-21
     */
    void remove(String key, long i, String value);

    /**
     * 范围检索
     * 
     * @Description:
     * @param key
     * @param start
     * @param end
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016-9-21
     */
    List<String> range(String key, int start, int end);

    /**
     * 入队
     * 
     * @Description:
     * @param key
     * @param value
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016-9-21
     */
    Long inQueue(String key, String value);

    /**
     * 出栈
     * 
     * @Description:
     * @param key
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016-9-21
     */
    String pop(String key);

    /**
     * @Description: 出队
     * @param key
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016-9-21
     */
    String outQueue(String key);

    /**
     * 
     * @Description: 单个hash记录存如到redis中
     * @param key
     * @param field
     * @param value
     * @Author:Huangjingle
     * @see:
     * @since: 1.0
     * @Create Date:2016年10月20日
     */
    void putHash(String key, Object field, Object value);

    /**
     * 
     * @Description: 根据key, field 获取hash的value
     * @param key
     * @param field
     * @return
     * @Author:Huangjingle
     * @see:
     * @since: 1.0
     * @Create Date:2016年10月20日
     */
    Object getHash(String key, Object field);

    /**
     * 
     * @Description: 根据key 获取hash里面的记录，返回是一个map<field, value>
     * @param key
     * @return
     * @Author:Huangjingle
     * @see:
     * @since: 1.0
     * @Create Date:2016年10月20日
     */
    Map entries(String key);

    /**
     * 
     * @Description: 根据key,field, 删除对应的hash记录
     * @param key
     * @param field
     * @return
     * @Author:Huangjingle
     * @see:
     * @since: 1.0
     * @Create Date:2016年10月20日
     */
    Long deleteHash(String key, Object field);

    /**
     * 
     * @Description: 查询当前期，只返回所有当前期的记录
     * @param keys
     *            如SS1、SS2，TT1、TT2、TT3
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016-10-21
     */
    List<Map> forEntries(List<String> keys);

    /**
     * 
     * @Description: 查询所有期数
     * @param keys
     *            如SS1 、SS2，TT1、TT2、TT3
     * @return
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016-10-21
     */
    List<Map> forAllEntries(List<String> keys);
}
