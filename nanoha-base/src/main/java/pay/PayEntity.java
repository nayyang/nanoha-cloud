package pay;

public class PayEntity {
    /**
     * 微信分配的公众账号ID（企业号corpid即为此appId）
     */
    public String appId;
    public String mchId;
    public String deviceInfo;
    public String nonceStr;
    public String sign;
    public String signType;
    public String body;
    public String detail;
    public String attach;
    public String outTradeNo;
    public String feeType;
    public int totalFee;
    /**
     * APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
     */
    public String spbillCreateIp;
}
