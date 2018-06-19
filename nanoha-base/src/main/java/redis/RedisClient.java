/**  
 *RedisClient.java         2016年9月19日上午11:44:15
 *@Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 *@Company:http://www.vivo.com.cn/
 * 
 */
package redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import content.Content;

/**
 * @Title:
 * @Description:
 * @Author:nanoha
 * @Since:2016年9月19日
 * @Version:1.0
 */
public class RedisClient {
    private final Jedis jedis;// 非切片额客户链接
    private JedisPool jedisPool;// 非切片连接池
    private final ShardedJedis shardedJedis;// 切片额客户端连接
    private ShardedJedisPool shardedJedisPool;// 切片连接池

    public RedisClient() {
        initialPool();
        initialShardedPool();
        this.shardedJedis = this.shardedJedisPool.getResource();
        this.jedis = this.jedisPool.getResource();
        this.jedis.auth(Content.Redis.PASSWORD);
    }

    /**
     * @Description: 初始化切片池
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月19日
     */
    private void initialShardedPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Content.Redis.MAXTOTAL);
        config.setMaxIdle(Content.Redis.MAXIDLE);
        config.setMaxWaitMillis(Content.Redis.MAXWAITMILLIS);
        config.setTestOnBorrow(Content.Redis.TESTONBORROW);
        // slave连接
        List<JedisShardInfo> shards = new ArrayList<>();
        shards.add(new JedisShardInfo(Content.Redis.HOST, Content.Redis.PORT, Content.Redis.NAME));
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    /**
     * @Description: 初始化非切片池
     * @Author:nanoha
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月19日
     */
    private void initialPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Content.Redis.MAXTOTAL);
        config.setMaxIdle(Content.Redis.MAXIDLE);
        config.setMaxWaitMillis(Content.Redis.MAXWAITMILLIS);
        config.setTestOnBorrow(Content.Redis.TESTONBORROW);
        jedisPool = new JedisPool(config, Content.Redis.HOST, Content.Redis.PORT);
    }

    public void close() {
        this.jedisPool.close();
        this.shardedJedisPool.close();
    }

    public static void main(String[] args) {
        RedisClient client = new RedisClient();
        Jedis jedis = client.jedis;
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key + "=====" + jedis.get(key));
        }
        client.close();

    }
}
