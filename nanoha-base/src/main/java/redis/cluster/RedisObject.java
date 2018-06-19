/**  
 *RedisObject.java         2016年7月6日下午3:07:50
 *@Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 *@Company:http://www.vivo.com.cn/
 * 
 */
package redis.cluster;

/**
 * @Title:
 * @Description:
 * @Author:zhangyi
 * @Since:2016年7月6日
 * @Version:1.0
 */
public class RedisObject {
    private String key;

    private String field;

    private String value;

    private Long incr;

    private Integer timeout;

    public RedisObject() {

    }

    public RedisObject(String key, String field, String value, Long incr, Integer timeout) {
        this.key = key;
        this.field = field;
        this.value = value;
        this.incr = incr;
        this.timeout = timeout;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * @param field
     *            the field to set
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the incr
     */
    public Long getIncr() {
        return incr;
    }

    /**
     * @param incr
     *            the incr to set
     */
    public void setIncr(Long incr) {
        this.incr = incr;
    }

    /**
     * @return the timeout
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * @param timeout
     *            the timeout to set
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

}
