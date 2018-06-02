package netty.redis;

import java.util.Map;
import java.util.Map.Entry;

import redis.clients.jedis.Jedis;

public class RedisService {
	private static Jedis jedis = new Jedis("127.0.0.1", 6379);

	public static void main(String[] args) {
		listPop();
	}

	static void listPop() {
		System.out.println(jedis.lpop("nihaoya"));
	}

	static void listPush() {
		jedis.lpush("nihaoya", "hee");
		jedis.lpush("nihaoya", "wohenhao");

		jedis.lpush("nilaile", "hehe");
		jedis.lpush("nilaile", "hehe2");
		jedis.lpush("nilaile", "hehe3");

		// jedis.lpop("nihaoya");
	}

	static void hash() {
		jedis.hset("nihaoya", "nilaile", "aha");
		jedis.hset("nihaoya", "nilaile", "aha2");
		jedis.hset("nihaoya", "nilaile", "aha3");
		jedis.hset("nihaoya", "nilaile", "aha4");
		jedis.hset("nihaoya", "nilaile", "aha5");

		Map<String, String> hgetAll = jedis.hgetAll("nihaoya");
		for (Entry<String, String> en : hgetAll.entrySet()) {
			System.out.println(en.getKey() + "____________" + en.getValue());
		}
	}

}
