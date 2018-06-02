package netty.recieve;

import java.io.Serializable;

import netty.utils.MD5;

public abstract class RecieveObject implements Serializable{
	private static final String KEY = "!a@d#f$g%v^g&m*4(q)q";
	private static final long serialVersionUID = -6480560006363368619L;
	
	/**初始化消息:第一次连接*/
	public static final int TYPE_INIT = 1;
	/**心跳消息 */
	public static final int TYPE_HEART = 2;
	/**文件读取异常消息 */
	public static final int TYPE_READ_EXP = 3;
	/**创建测试消息 */
	public static final int TYPE_TEST_FILE = 99;
	
	private int type;//接收消息类型
	private String desc;//消息描述
	private String mchId;//商户id
	private String devId;// 设备id
	private String sign;//签名信息
	private Long time;//时间
	
	/**获取对象接收类型
	 * @author lomner
	 * @date 2017年6月26日 下午6:49:34
	 * @return
	 */
	public abstract int getRecieveType();
	
	/**是否同类消息
	 * @author lomner
	 * @date 2017年6月26日 下午6:50:52
	 * @return
	 */
	public boolean isSameType(){
		if(type == getRecieveType()){
			return true;
		}
		return false;
	}
	
	public boolean isSignTrue(){
		if(sign != null && sign.equals(createSign())){
			return true;
		}
		return false;
	}
	
	public String createSign(){
		String waitSign = KEY + "," + mchId + "," + devId + "," + time;
		return MD5.MD5Encode(waitSign);
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
}
