package com.redhat.developer.demos;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
 import javax.jms.Message;

import org.apache.qpid.jms.JmsConnectionFactory;

public class JMSReciever {
    public static void main(String[] args) throws Exception {
        System.out.println("Receiving a JMS Message");

        Connection connection = null;
        
        try {
            Context context = new InitialContext();
            ConnectionFactory factory = (ConnectionFactory) context.lookup("myFactoryLookup");
            Destination queue = (Destination) context.lookup("myQueueLookup");
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageConsumer messageConsumer = session.createConsumer(queue);
            
            Message m = messageConsumer.receive(1000);  // 1 sec timeout
            if (m != null ) {
                String msgBody = ((TextMessage) m).getText();
                System.out.println("message = " + msgBody);
            }
            else 
                System.out.println("Nothing Received");    
   
        } finally {
         if (connection != null) {            
            connection.close();
         }
      }
    }
}