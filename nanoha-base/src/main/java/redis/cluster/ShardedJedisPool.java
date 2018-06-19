package redis.cluster;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.util.Hashing;
import redis.clients.util.Pool;

/**
 * encapsulation the ShardedJedis pool to spring
 * 
 * @author daixing
 *
 */
public class ShardedJedisPool extends Pool<ShardedJedis> {

    public ShardedJedisPool(final GenericObjectPoolConfig poolConfig, String hosts, String password, Integer timeOut) {
        this(poolConfig, hosts, password, timeOut, Hashing.MURMUR_HASH);
    }

    public ShardedJedisPool(final GenericObjectPoolConfig poolConfig, String hosts, String password, Integer timeOut,
            Hashing algo) {
        this(poolConfig, hosts, password, timeOut, algo, null);
    }

    public ShardedJedisPool(final GenericObjectPoolConfig poolConfig, String hosts, String password, Integer timeOut,
            Pattern keyTagPattern) {
        this(poolConfig, hosts, password, timeOut, Hashing.MURMUR_HASH, keyTagPattern);
    }

    public ShardedJedisPool(final GenericObjectPoolConfig poolConfig, String hosts, String password, Integer timeOut,
            Hashing algo, Pattern keyTagPattern) {
        super(poolConfig, new ShardedJedisFactory(null, hosts, password, timeOut, Hashing.MURMUR_HASH, keyTagPattern));
    }

    @Override
    public ShardedJedis getResource() {
        ShardedJedis jedis = super.getResource();
        jedis.setDataSource(this);
        return jedis;
    }

    /**
     * @deprecated starting from Jedis 3.0 this method won't exist. Resouce
     *             cleanup should be done using @see
     *             {@link redis.clients.jedis.ShardedJedis#close()}
     */
    @Deprecated
    @Override
    public void returnBrokenResource(final ShardedJedis resource) {
        if (resource != null) {
            returnBrokenResourceObject(resource);
        }
    }

    /**
     * starting from Jedis 3.0 this method won't exist. Resouce cleanup should
     * be done using @see {@link redis.clients.jedis.ShardedJedis#close()}
     */
    @Override
    public void returnResource(final ShardedJedis resource) {
        if (resource != null) {
            resource.resetState();
            returnResourceObject(resource);
        }
    }

    /**
     * PoolableObjectFactory custom impl.
     */
    private static class ShardedJedisFactory implements PooledObjectFactory<ShardedJedis> {
        private String hosts;
        private String password;
        private Integer timeOut;
        private Hashing algo;
        private Pattern keyTagPattern;
        private List<JedisShardInfo> shards;

        public ShardedJedisFactory(List<JedisShardInfo> shards, String hosts, String password, Integer timeOut,
                Hashing algo, Pattern keyTagPattern) {
            this.hosts = hosts;
            this.password = password;
            this.timeOut = timeOut;
            this.algo = algo;
            this.keyTagPattern = keyTagPattern;
            initShards();
        }

        private void initShards() {
            shards = new ArrayList<>();
            if (hosts != null && hosts.indexOf(":") > 0) {
                JedisShardInfo jedisShardInfo = null;
                for (String host : hosts.split(",")) {
                    String[] ary = host.split(":");
                    // if set host must be different value may be cause
                    // non-uniform distribution
                    // 设置name为host时，不同环境的name可能为空
                    jedisShardInfo = new JedisShardInfo(ary[0], Integer.valueOf(ary[1]), timeOut);
                    if (StringUtils.isNotBlank(password)) {
                        jedisShardInfo.setPassword(password);
                    }
                    shards.add(jedisShardInfo);
                }
            }
        }

        @Override
        public PooledObject<ShardedJedis> makeObject() throws Exception {
            ShardedJedis jedis = new ShardedJedis(shards, algo, keyTagPattern);
            return new DefaultPooledObject<ShardedJedis>(jedis);
        }

        @Override
        public void destroyObject(PooledObject<ShardedJedis> pooledShardedJedis) throws Exception {
            final ShardedJedis shardedJedis = pooledShardedJedis.getObject();
            for (Jedis jedis : shardedJedis.getAllShards()) {
                try {
                    try {
                        jedis.quit();
                    } catch (Exception e) {

                    }
                    jedis.disconnect();
                } catch (Exception e) {

                }
            }
        }

        @Override
        public boolean validateObject(PooledObject<ShardedJedis> pooledShardedJedis) {
            try {
                ShardedJedis jedis = pooledShardedJedis.getObject();
                for (Jedis shard : jedis.getAllShards()) {
                    if (!shard.ping().equals("PONG")) {
                        return false;
                    }
                }
                return true;
            } catch (Exception ex) {
                return false;
            }
        }

        @Override
        public void activateObject(PooledObject<ShardedJedis> p) throws Exception {

        }

        @Override
        public void passivateObject(PooledObject<ShardedJedis> p) throws Exception {

        }
    }
}
