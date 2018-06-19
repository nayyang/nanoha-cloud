package redis.cluster;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanNameAware;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.util.SafeEncoder;

import com.alibaba.fastjson.JSON;

@RedisAround
public class ShardedManager implements BeanNameAware {
    private static final Logger log = LogManager.getLogger(ShardedManager.class);

    private ShardedJedisPool jedisPool;

    public ShardedJedis getResource() {
        try {
            return ShardHolder.getShardedJedis();
        } catch (Exception e) {
            log.error("get jedis error!", e);
        }
        return null;
    }

    protected void returnSource() {
        try {
            if (getResource() != null) {
                jedisPool.returnResource(getResource());
            }
        } catch (Exception e) {
            log.error("return jedis to the pool error!", e);
        }
    }

    // key start
    public Long del(String key) {
        return getResource().del(key);
    }

    public boolean exists(String key) {
        return getResource().exists(key);
    }

    public Long expire(String key, int second) {
        return getResource().expire(key, second);
    }

    // key end

    // string start
    public Long decr(String key) {
        return getResource().decr(key);
    }

    public Long decrBy(String key, long num) {
        return getResource().decrBy(key, num);
    }

    public Long incr(String key) {
        return getResource().incr(key);
    }

    public Long incrBy(String key, long num) {
        return getResource().incrBy(key, num);
    }

    public String get(String key) {
        return getResource().get(key);
    }

    public String set(String key, String value) {
        return getResource().set(key, value);
    }

    public Long setnx(String key, String value) {
        return getResource().setnx(key, value);
    }

    public String getrange(String key, long startOffset, long endOffset) {
        return getResource().getrange(key, startOffset, endOffset);
    }

    public String getSet(String key, String value) {
        return getResource().getSet(key, value);
    }

    // string end

    // set start
    public boolean sismember(String key, String value) {
        return getResource().sismember(key, value);
    }

    public Long sadd(String key, String... members) {
        return getResource().sadd(key, members);
    }

    public Long scard(String key) {
        return getResource().scard(key);
    }

    public void srem(String key, String value) {
        getResource().srem(key, value);
    }

    public List<String> sscan(String key, int count) {
        Jedis jedis = getResource().getShard(key);
        ScanParams scanParams = new ScanParams();
        scanParams.count(count);
        List<String> result = new ArrayList<String>();
        ScanResult<String> scanResult;
        String cursor = "0";
        do {
            scanResult = jedis.sscan(key, cursor, scanParams);
            cursor = scanResult.getStringCursor();
            result.addAll(scanResult.getResult());
        } while (Long.parseLong(cursor) > 0);
        return result;
    }

    // set end

    // hash start
    public Long hdel(String key, String... fields) {
        return getResource().hdel(key, fields);
    }

    public Boolean hexists(String key, String field) {
        return getResource().hexists(key, field);
    }

    public String hget(String key, String field) {
        return getResource().hget(key, field);
    }

    public byte[] hgetBytes(String key, String field) {
        return getResource().hget(SafeEncoder.encode(key), SafeEncoder.encode(field));
    }

    public Map<String, String> hgetAll(String key) {
        return getResource().hgetAll(key);
    }

    public Long hincrBy(String key, String field, long value) {
        return getResource().hincrBy(key, field, value);
    }

    public Set<String> hkeys(String key) {
        return getResource().hkeys(key);
    }

    public Long hlen(String key) {
        return getResource().hlen(key);
    }

    public List<String> hmget(String key, String... fields) {
        return getResource().hmget(key, fields);
    }

    public String hmset(String key, Map<String, String> hash) {
        return getResource().hmset(key, hash);
    }

    public Long hset(String key, String field, String value) {
        return getResource().hset(key, field, value);
    }

    public Long hset(String key, String field, byte[] value) {
        return getResource().hset(SafeEncoder.encode(key), SafeEncoder.encode(field), value);
    }

    public boolean hsetnx(String key, String field, String value) {
        return longToBoolean(getResource().hsetnx(key, field, value));
    }

    public boolean hsetnx(String key, String field, byte[] value) {
        return longToBoolean(getResource().hsetnx(SafeEncoder.encode(key), SafeEncoder.encode(field), value));
    }

    public List<String> hvals(String key) {
        return getResource().hvals(key);
    }

    public List<Map.Entry<String, String>> hscan(String key, int count) {
        Jedis jedis = getResource().getShard(key);
        ScanParams scanParams = new ScanParams();
        scanParams.count(count);
        List<Map.Entry<String, String>> result = new ArrayList<Map.Entry<String, String>>();
        ScanResult<Map.Entry<String, String>> scanResult;
        String cursor = "0";
        do {
            scanResult = jedis.hscan(key, cursor, scanParams);
            cursor = scanResult.getStringCursor();
            result.addAll(scanResult.getResult());
        } while (Long.parseLong(cursor) > 0);
        return result;
    }

    public List<Map.Entry<byte[], byte[]>> hscanBytes(String key, int count) {
        Jedis jedis = getResource().getShard(key);
        ScanParams scanParams = new ScanParams();
        scanParams.count(count);
        List<Map.Entry<byte[], byte[]>> result = new ArrayList<Map.Entry<byte[], byte[]>>();
        ScanResult<Map.Entry<byte[], byte[]>> scanResult;
        String cursor = "0";
        do {
            scanResult = jedis.hscan(SafeEncoder.encode(key), SafeEncoder.encode(cursor), scanParams);
            cursor = scanResult.getStringCursor();
            result.addAll(scanResult.getResult());
        } while (Long.parseLong(cursor) > 0);
        return result;
    }

    // hash end

    // list start
    public String lpop(String key) {
        return getResource().lpop(key);
    }

    public Long lpush(String key, String... strings) {
        return getResource().lpush(key, strings);
    }

    public Long lpushx(String key, String string) {
        return getResource().lpushx(key, string);
    }

    public List<String> lrange(String key, long start, long end) {
        return getResource().lrange(key, start, end);
    }

    public Long lrem(String key, long count, String value) {
        return getResource().lrem(key, count, value);
    }

    public String lset(String key, long index, String value) {
        return getResource().lset(key, index, value);
    }

    public String ltrim(String key, long start, long end) {
        return getResource().ltrim(key, start, end);
    }

    public String rpop(String key) {
        return getResource().rpop(key);
    }

    public Long rpush(String key, String... strings) {
        return getResource().rpush(key, strings);
    }

    public Long rpushx(String key, String... strings) {
        return getResource().rpush(key, strings);
    }

    // list end

    private boolean longToBoolean(Long val) {
        return val != null && val.equals(1l);
    }

    public void setJedisPool(ShardedJedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public ShardedJedisPool getJedisPool() {
        return jedisPool;
    }

    @Override
    public void setBeanName(String name) {
        ShardHolder.addShardPool(name, this.getJedisPool());
    }

    public Logger getLog() {
        return log;
    }

    public <T> boolean flush(String key, T t) {
        if (t == null) {
            return false;
        }
        try {
            if (t instanceof String) {
                set(key, (String) t);
            } else {
                // 原有的包找不到，使用fastjson
                // set(key, JackSonParser.bean2Json(t));
                set(key, JSON.toJSONString(t));
            }

            return true;
        } catch (Exception e) {
            log.error("缓存刷新失败!", e);
        }
        return false;
    }

    public boolean delete(String key) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        try {
            del(key);
            return true;
        } catch (Exception e) {
            log.error("删除缓存失败!", e);
        }
        return false;
    }

    public <T> T get(String key, Class<T> clazz) {
        try {
            String json = get(key);
            if (StringUtils.isNotEmpty(json)) {
                return JSON.parseObject(json, clazz);
            }
        } catch (Exception e) {
            log.error("获取信息失败!", e);
        }
        return null;
    }

    public String getStr(String key) {
        try {
            return get(key);
        } catch (Exception e) {
            log.error("获取字符串信息失败!", e);
        }
        return null;
    }

    public boolean hashIncr(String key, String field, Long incr) {
        try {
            hincrBy(key, field, incr);
            return true;
        } catch (Exception e) {
            log.error("Hash自增失败!", e);
        }
        return false;
    }

    public <T> boolean hashSet(String key, String field, T t) {
        try {
            hset(key, field, t.toString());
            return true;
        } catch (Exception e) {
            log.error("更新缓存失败!", e);
        }
        return false;
    }

    public Long hashgetLong(String key, String field) {
        try {
            String val = hget(key, field);
            if (StringUtils.isNotEmpty(val)) {
                return Long.parseLong(val);
            }
        } catch (Exception e) {
            log.error("更新缓存失败!", e);
        }
        return null;
    }

    // 初始化key
    /*
     * public void initialRedisRecordCount(String hashKey, String name, Integer
     * timeout) { if (!exists(hashKey)) { Map<String, String> adMap = new
     * HashMap<String, String>(); adMap.put(RedisConstants.ID_REDIS_VALUE_NAME,
     * name); adMap.put(RedisConstants.LOAD_COUNT_REDIS_VALUE_NAME,
     * RedisConstants.COUNT_INITIAL_VALUE);
     * adMap.put(RedisConstants.SHOW_COUNT_REDIS_VALUE_NAME,
     * RedisConstants.COUNT_INITIAL_VALUE);
     * adMap.put(RedisConstants.CLICK_COUNT_REDIS_VALUE_NAME,
     * RedisConstants.COUNT_INITIAL_VALUE);
     * adMap.put(RedisConstants.COST_COUNT_REDIS_VALUE_NAME,
     * RedisConstants.COUNT_INITIAL_VALUE); hmset(hashKey, adMap);
     * expire(hashKey, timeout); } }
     */

    public <T> List<T> getList(List<String> keys, Class<T> clazz) {
        try {
            ShardedJedisPipeline pipeline = getResource().pipelined();
            for (String key : keys) {
                pipeline.get(key);
            }
            List<Object> results = pipeline.syncAndReturnAll();
            if (null != results && results.size() > 0) {
                List<T> resultList = new ArrayList<T>();
                for (Object result : results) {
                    resultList.add(JSON.parseObject(result.toString(), clazz));
                }
                return resultList;
            }

        } catch (Exception e) {
            log.error("获取缓存列表出错!", e);
        }
        return null;
    }

    public void setKeys(List<RedisObject> listRedis) {
        try {
            if (null != listRedis && listRedis.size() > 0) {
                ShardedJedisPipeline pipeline = getResource().pipelined();
                for (RedisObject RedisObject : listRedis) {
                    if (StringUtils.isNotEmpty(RedisObject.getKey())) {
                        if (StringUtils.isNotEmpty(RedisObject.getField())) { // hash
                            if (StringUtils.isNotEmpty(RedisObject.getValue())) {
                                pipeline.hset(RedisObject.getKey(), RedisObject.getField(), RedisObject.getValue());
                            }
                            if (null != RedisObject.getIncr()) {
                                pipeline.hincrBy(RedisObject.getKey(), RedisObject.getField(), RedisObject.getIncr());
                            }
                        } else {// String
                            pipeline.set(RedisObject.getKey(), RedisObject.getValue());
                        }
                        if (null != RedisObject.getTimeout()) {
                            pipeline.expire(RedisObject.getKey(), RedisObject.getTimeout());
                        }
                    }
                }
                pipeline.syncAndReturnAll();
            }
        } catch (Exception e) {
            log.error("管道批量写入缓存列表出错!", e);
        }
    }

}
