package netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * 入口
 * 
 * @author lim58
 *
 */
public class NettyServer {
	private static final int BIZTHREADSIZE = 100;
	private static final int port = 8989;

	public void nettyServer() {
		EventLoopGroup bossGroup = new NioEventLoopGroup(BIZTHREADSIZE);
		EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup);
			serverBootstrap.channel(NioServerSocketChannel.class);
			serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
			// handler监听服务端的io动作
			serverBootstrap.childHandler(new ChildChannelHandler());
			// 绑定端口

			InetSocketAddress inetSocketAddress = new InetSocketAddress(8989);

			ChannelFuture channelFuture = serverBootstrap.bind(inetSocketAddress).sync();

			// 等待服务监听端口关闭
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// 退出
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			// 初始化编码
			ChannelPipeline pipeline = ch.pipeline();
			// handler监听客户端channel
			pipeline.addLast(new SimpleServerHandler());
		}

	}

	public static void main(String[] args) {
		new NettyServer().nettyServer();
	}
}
