/**  
 *JMSProducer.java         2016年9月18日下午2:27:30
 *@Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 *@Company:http://www.vivo.com.cn/
 * 
 */
package jms.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import content.Content;

/**
 * @Title: 消息生产者
 * @Description:
 * @Author:nayyang
 * @Since:2016年9月18日
 * @Version:1.0
 */
public class JMSProducer {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageProducer messageProducer;
        connectionFactory = new ActiveMQConnectionFactory(Content.JMS.USERNAME, Content.JMS.PASSWORD,
                Content.JMS.BROKEURL);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("hello");
            messageProducer = session.createProducer(destination);
            sendMessage(session, messageProducer);
            session.commit();
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

    /**
     * @Description: 发送消息
     * @param session
     * @param messageProducer
     * @throws JMSException
     * @Author:nayyang
     * @see:
     * @since: 1.0
     * @Create Date:2016年9月18日
     */
    private static void sendMessage(Session session, MessageProducer messageProducer) throws JMSException {
        for (int i = 0; i < Content.JMS.SENDNUM; i++) {
            TextMessage message = session.createTextMessage("发送消息" + i);
            System.out.println("发送消息" + i);
            messageProducer.send(message);
        }
    }
}
