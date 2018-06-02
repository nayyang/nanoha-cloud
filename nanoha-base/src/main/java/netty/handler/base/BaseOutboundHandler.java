package netty.handler.base;

import com.alibaba.fastjson.JSONObject;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * 基础输出handler
 * 
 * @author lim58
 *
 */
public abstract class BaseOutboundHandler extends ChannelOutboundHandlerAdapter {
	public abstract int getType();

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		String writeMsg = (String) msg;
		JSONObject object = JSONObject.parseObject(writeMsg);

		if (getType() != object.getIntValue("type")) {
			super.write(ctx, msg, promise);
			return;
		}

		doWrite(ctx, writeMsg + "\r\n");
	}

	public abstract void doWrite(ChannelHandlerContext ctx, String msg) throws Exception;
}
