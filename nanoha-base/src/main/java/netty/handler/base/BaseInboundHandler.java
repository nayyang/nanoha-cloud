package netty.handler.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.alibaba.fastjson.JSONObject;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.cache.ChannelCache;
import netty.log.SocketLog;
import netty.recieve.RecieveObject;

/**
 * 基础输入handler
 * 
 * @author lim58
 *
 * @param <T>
 */
public abstract class BaseInboundHandler<T extends RecieveObject> extends ChannelInboundHandlerAdapter {
	/**
	 * 设备id
	 */
	protected String devId;

	@SuppressWarnings("unchecked")
	private Class<T> tClass() {
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType p = (ParameterizedType) type;
		Class<T> c = (Class<T>) p.getActualTypeArguments()[0];
		return c;
	}

	public abstract void doRead(ChannelHandlerContext ctx, T t, String msg) throws Exception;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String recieved = (String) msg;
		T t = JSONObject.parseObject(recieved, tClass());

		if (!t.isSameType()) {// 消息对象和handler类别不一致时,跳转到下一个handler
			ctx.fireChannelRead(msg);
			return;
		}

		if (!t.isSignTrue()) {// 签名不一致时,关闭连接
			SocketLog.server().info("通道关闭--签名失败:mchId=" + t.getMchId() + ",parkingId=" + t.getDevId() + ",time="
					+ t.getTime() + ",sign=" + t.getSign());
			ctx.close();
			ChannelCache.remove(t.getDevId());// 清除缓存
			return;
		}

		// 设置车场id
		setParkingId(t.getDevId());

		// 新增缓存
		ChannelCache.put(t.getDevId(), ctx.channel());

		// 执行read操作
		doRead(ctx, t, recieved);

		// 跳转到下一个handler
		ctx.fireChannelRead(msg);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}


	public String getParkingId() {
		if (this.devId == null) {
			return "未知";
		}
		return devId;
	}

	public void setParkingId(String parkingId) {
		if (this.devId == null) {
			this.devId = parkingId;
		}
	}
}
