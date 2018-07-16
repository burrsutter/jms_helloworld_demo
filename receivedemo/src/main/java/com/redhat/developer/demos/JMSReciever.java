package com.redhat.developer.demos;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.jms.JmsConnectionFactory;

public class JMSReciever {
    public static void main(String[] args) throws Exception {
        System.out.println("Receiving some JMS 2.0?");

        Connection connection = null;
        ConnectionFactory connectionFactory = new JmsConnectionFactory("amqp://localhost:5672");
        
        try {
            connection = connectionFactory.createConnection();
            connection.start();    

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("exampleQueue");
            MessageConsumer consumer = session.createConsumer(queue);
            TextMessage m = (TextMessage) consumer.receive(5000);
            if (m != null )
                System.out.println("message = " + m.getText());
            else 
                System.out.println("Nothing Received");    
   
        } finally {
         if (connection != null) {            
            connection.close();
         }
      }
    }
}