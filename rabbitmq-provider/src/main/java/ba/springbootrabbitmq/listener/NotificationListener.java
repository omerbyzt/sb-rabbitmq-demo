//package ba.springbootrabbitmq.listener;
//
//import ba.springbootrabbitmq.model.Notification;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NotificationListener {
//
//    @RabbitListener(queues = "omerb-queue")
//    public void handleMessage(Notification notification){
//        System.out.println("Message received...!");
//        System.out.println(notification.toString());
//    }
//}