package netty.cache;

import org.apache.commons.lang3.SerializationUtils;

import io.netty.channel.Channel;
import netty.redis.RedisService;
import netty.utils.SerizlizeUtils;

import java.util.HashMap;
import java.util.Map;

public class ChannelCache {

	private static Map<String,Channel> channelCacheMap = new HashMap<String, Channel>();


	public static Channel get(String channelCacheKey) {
		return (Channel) channelCacheMap.get(channelCacheKey);
	}

	public static void put(String channelCacheKey, Channel channel) {
		channelCacheMap.put(channelCacheKey,channel);
	}

	public static void remove(String channelCacheKey) {
		channelCacheMap.remove(channelCacheKey);
	}

}
