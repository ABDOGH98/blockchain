package com.blockchain_backend.Configuration;



import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.hibernate.Transaction;


import javax.jms.*;
import java.io.IOException;


public class Miner {

    public void getTransactions() throws Exception{
        try {
            //Connect to ActiveMQ broker
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            //Create session for sending data
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //Sub to the queue
            Destination destination = session.createTopic("mempool.topic");
            //connect consumer with the queue
            MessageConsumer consumer = session.createConsumer(destination);
            //Receive message with specific property
            //##### MessageConsumer consumer = session.createConsumer(destination,"code='c1'"); ####
            //event handling message consumed
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message t) {
                    System.out.print("recieve message : ");
                    ObjectMapper objectMapper=new ObjectMapper();
                    Transaction transaction = null;
                    try {
                        transaction = objectMapper.readValue((JsonParser) t, Transaction.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(transaction.toString());
                }

            });
        }catch (JMSException e){
            e.printStackTrace();
        }
    }
}
