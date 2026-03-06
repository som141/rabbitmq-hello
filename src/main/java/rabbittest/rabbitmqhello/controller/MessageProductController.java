package rabbittest.rabbitmqhello.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rabbittest.rabbitmqhello.config.RabbitMQConfig;
import rabbittest.rabbitmqhello.dto.OrderDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MessageProductController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/send/text")
    public String sendText(@RequestParam String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,
                "hello.key.11",
                message);

        return "Text meessage = "+message;
    }
    @GetMapping("send/order")
    public String sendOrder() {
        OrderDto order = new OrderDto(UUID.randomUUID().toString(),
                "Spring in Action",
                 new BigDecimal(1000),
                1,
                LocalDateTime.now());

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,
                "hello.key.order.12",
                order
        );

        return "Order created = "+order;
    }
}
