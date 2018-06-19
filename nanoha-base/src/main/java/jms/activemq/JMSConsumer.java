/**  
 *JMSConsumer.java         2016年9月18日下午5:20:15
 *@Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 *@Company:http://www.vivo.com.cn/
 * 
 */
package jms.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import content.Content;

/**
 * @Title: 消费者
 * @Description:
 * @Author:nayyang
 * @Since:2016年9月18日
 * @Version:1.0
 */
public class JMSConsumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageConsumer messageConsumer;
        connectionFactory = new ActiveMQConnectionFactory(Content.JMS.USERNAME, Content.JMS.PASSWORD,
                Content.JMS.BROKEURL);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("hello");
            messageConsumer = session.createConsumer(destination);
            while (true) {
                TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
                if (textMessage != null) {
                    System.out.println("接收消息：" + textMessage.getText());
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
