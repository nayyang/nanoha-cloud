/**  
 *Content.java         2016年9月18日下午5:21:03
 *@Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 *@Company:http://www.vivo.com.cn/
 * 
 */
package content;

import org.apache.activemq.ActiveMQConnection;

/**
 * @Title:
 * @Description:
 * @Author:nayyang
 * @Since:2016年9月18日
 * @Version:1.0
 */
public interface Content {
    interface JMS {
        String USERNAME = ActiveMQConnection.DEFAULT_USER;
        String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
        String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
        int SENDNUM = 10;
    }

    interface Redis {
        String HOST = "192.168.1.195";
        int PORT = 6379;
        int MAXTOTAL = 20;
        long MAXWAITMILLIS = 10001;
        int MAXIDLE = 5;
        boolean TESTONBORROW = false;
        String NAME = "master";
        String PASSWORD = "admin123";
    }
}
