package netty.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**socket日志
 * @author lomner
 * @date 2017年6月26日 下午12:20:04
 *
 */
public class SocketLog {
	private static final String NETTY_SERVER = "netty.server";
	private static final String NETTY_HEART = "netty.heart";
	private static final String NETTY_SEND = "netty.send";
	private static final String NETTY_READ_FILE = "netty.read.file";
	private static final String NETTY_TEST_FILE = "netty.test.file";
	
	
	/**服务器日志
	 * @author lomner
	 * @date 2017年6月26日 下午12:19:25
	 * @return
	 */
	public static Logger server(){
		return LoggerFactory.getLogger(NETTY_SERVER);
	}
	
	/**心跳异常日志
	 * @author lomner
	 * @date 2017年6月26日 下午12:19:33
	 * @return
	 */
	public static Logger heart(){
		return LoggerFactory.getLogger(NETTY_HEART);
	}
	
	/**文件下发日志
	 * @author lomner
	 * @date 2017年6月26日 下午12:19:43
	 * @return
	 */
	public static Logger send(){
		return LoggerFactory.getLogger(NETTY_SEND);
	}
	
	/**文件读取异常日志
	 * @author lomner
	 * @date 2017年6月26日 下午12:19:50
	 * @return
	 */
	public static Logger readFile(){
		return LoggerFactory.getLogger(NETTY_READ_FILE);
	}
	
	/**文件读取异常日志
	 * @author lomner
	 * @date 2017年6月26日 下午12:19:50
	 * @return
	 */
	public static Logger testFile(){
		return LoggerFactory.getLogger(NETTY_TEST_FILE);
	}
}
