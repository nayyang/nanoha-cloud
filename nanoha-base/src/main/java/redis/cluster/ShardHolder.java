/**  
 *ShardHolder.java         2016年4月15日下午4:58:44
 *@Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 *@Company:http://www.vivo.com.cn/
 * 
 */
package redis.cluster;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import redis.clients.jedis.ShardedJedis;

/**
 * @Title:
 * @Description:
 * @Author:daixing
 * @Since:2016年4月15日
 * @Version:1.0
 */
public class ShardHolder {

    private static final ThreadLocal<ShardedJedis> JEDISLOCAL = new ThreadLocal<ShardedJedis>();

    // private static final ThreadLocal<Integer> count = new
    // ThreadLocal<Integer>();

    private static final Map<String, ShardedJedisPool> SHARDMAP = new HashMap<String, ShardedJedisPool>();

    private static ShardedJedisPool defaultPool;

    private ShardHolder() {
        super();
    }

    protected static void createShardedJedis(String key) {
        // incrCount();
        ShardedJedisPool shardPool = getShardPool(key);
        if (shardPool != null) {
            JEDISLOCAL.set(shardPool.getResource());
        } else {
            JEDISLOCAL.set(defaultPool.getResource());
        }

    }

    protected static void returnShardedJedis(String key) {
        ShardedJedisPool shardPool = getShardPool(key);
        ShardedJedis shardedJedis = JEDISLOCAL.get();
        if (shardPool != null && shardedJedis != null) {
            shardPool.returnResource(shardedJedis);
        }
        // count.set(count.get() - 1);
    }

    protected static ShardedJedis getShardedJedis() {
        return JEDISLOCAL.get();
    }

    // protected static Integer getCount() {
    // return count.get();
    // }

    protected static void addShardPool(String key, ShardedJedisPool pool) {
        SHARDMAP.put(key, pool);
        defaultPool = pool;
    }

    protected static ShardedJedisPool getShardPool(String key) {
        if (StringUtils.isNotBlank(key)) {
            return SHARDMAP.get(key);
        }
        return defaultPool;
    }
    //
    // protected static void incrCount() {
    // if (JEDISLOCAL.get() != null) {
    // count.set(getCount() + 1);
    // return;
    // } else {
    // count.set(1);
    // }
    // }
    //
    // protected static void decrCount() {
    // count.set(getCount() - 1);
    // }

}
