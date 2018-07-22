package com.redhat.developer.demos;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Destination;
// import javax.jms.TextMessage;


import org.apache.qpid.jms.JmsConnectionFactory;

public class JMSSender {
    public static void main(String[] args) throws Exception {
        System.out.println("Sending a JMS message");

        Connection connection = null;
        try {
            Context context = new InitialContext();
            ConnectionFactory factory = (ConnectionFactory) context.lookup("myFactoryLookup");
            Destination queue = (Destination) context.lookup("myQueueLookup");
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            messageProducer.send(session.createTextMessage("Hello world "));
            System.out.println("message sent check - http://localhost:8161/console");
        } finally {
         if (connection != null) {            
            connection.close();
         }
      }
    }
}