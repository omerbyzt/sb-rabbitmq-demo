package ba.springbootrabbitmq.producer;

import ba.springbootrabbitmq.model.Notification;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/notification")
public class NotificationProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${sr.rabbit.routing.name}")
    private String routingName;

    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;

    @GetMapping
    public ResponseEntity<String> getNotification(@RequestParam String str){

        Notification notification = new Notification();
        notification.setNotificationId(UUID.randomUUID().toString());
        notification.setCreatedAt(new Date());
        notification.setMessage(str);
        notification.setSeen(Boolean.FALSE);

        sendToQueue(notification);
        return ResponseEntity.ok(str);
    }

    public void sendToQueue(Notification notification){
        System.out.println("Notification Sent ID : " + notification.getNotificationId());
        amqpTemplate.convertAndSend(exchangeName, routingName, notification);
    }
}
