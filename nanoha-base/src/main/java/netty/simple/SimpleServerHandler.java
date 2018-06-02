package netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import netty.cache.ChannelCache;

import java.util.Scanner;

/**
 * server接收消息处理handler
 * 
 * @author lim58
 *
 */
public class SimpleServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf msgBuf = (ByteBuf) msg;
		byte[] msgByte = new byte[msgBuf.readableBytes()];
		// msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
		msgBuf.readBytes(msgByte);
		String message = new String(msgByte, CharsetUtil.UTF_8);

		System.out.println("Netty-Server, Receive Message: " + message);
		msgBuf.release();

		ChannelCache.put("11111",ctx.channel());

		 Scanner scanner = new Scanner(System.in);
		 String response = scanner.nextLine();
		 ByteBuf respBuf = ctx.alloc().buffer(4 * response.length());
		 respBuf.writeBytes(response.getBytes());
		//发送
		 ctx.writeAndFlush(respBuf);

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + "链接成功");
	}
}
