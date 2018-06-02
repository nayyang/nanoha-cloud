package netty.simple;

import java.util.Scanner;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 客户端handler
 * 
 * @author lim58
 *
 */
public class SimpleClientHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String response = "hey";
		ByteBuf respBuf = ctx.alloc().buffer(4 * response.length());
		respBuf.writeBytes(response.getBytes());
		ctx.writeAndFlush(respBuf);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ByteBuf msgBuf = (ByteBuf) msg;
		byte[] msgByte = new byte[msgBuf.readableBytes()];
		// msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
		msgBuf.readBytes(msgByte);
		String message = new String(msgByte, CharsetUtil.UTF_8);

		System.out.println("Netty-Client, Receive Message: " + message);
		msgBuf.release();

		Scanner scanner = new Scanner(System.in);

		while (true) {
			String response = scanner.nextLine();
			ByteBuf respBuf = ctx.alloc().buffer(4 * response.length());
			respBuf.writeBytes(response.getBytes());
			ctx.writeAndFlush(respBuf);
			if (response.equals("bye")){
				break;
			}
		}



	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
