package com.blockchain_backend.Configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;


public class MyConfig {

    public Queue queue() {
        return new ActiveMQQueue("mempool.queue");
    }
}
