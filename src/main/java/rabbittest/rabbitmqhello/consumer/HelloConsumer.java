package rabbittest.rabbitmqhello.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rabbittest.rabbitmqhello.config.RabbitMQConfig;
import rabbittest.rabbitmqhello.dto.OrderDto;

@Slf4j
@Component
public class HelloConsumer {
//    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
//    public void receiveMessage(String message){
//        log.info("Received massage: {}",message);
//    }
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(OrderDto message) {
        log.info("Received message: {}", message.toString());
    }
}
