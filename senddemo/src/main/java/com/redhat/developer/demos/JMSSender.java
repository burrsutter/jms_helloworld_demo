package com.redhat.developer.demos;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
// import javax.jms.TextMessage;


import org.apache.qpid.jms.JmsConnectionFactory;

public class JMSSender {
    public static void main(String[] args) throws Exception {
        System.out.println("Sending some JMS 2.0?");

        Connection connection = null;
        ConnectionFactory connectionFactory = new JmsConnectionFactory("amqp://localhost:5672");
        
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("exampleQueue");
            MessageProducer sender = session.createProducer(queue);
            sender.send(session.createTextMessage("Hello world "));
            System.out.println("message sent");
        } finally {
         if (connection != null) {            
            connection.close();
         }
      }
    }
}