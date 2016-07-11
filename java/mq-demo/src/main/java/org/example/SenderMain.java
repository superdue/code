package org.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class SenderMain {

    private static Log LOGGER = LogFactory.getLog(SenderMain.class);

    public static void main(String[] args) throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("sender-application.xml");

        final Topic myTopic = ctx.getBean("myTopic", Topic.class);
        final JmsTemplate jmsTemplate = ctx.getBean("jmsTemplate", JmsTemplate.class);

        jmsTemplate.send(myTopic, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("name", "zhangsan");
                message.setInt("id", 1);
                return message;
            }
        });

    }

}
