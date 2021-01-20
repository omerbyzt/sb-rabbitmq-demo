package com.ba.rabbitmqconsumer.listener;

import com.ba.rabbitmqconsumer.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = "${sb.rabbitmq.queue}")
    public void handleMessage(Notification notification){
        System.out.println("Message received...!");
        System.out.println(notification.toString());
    }
}