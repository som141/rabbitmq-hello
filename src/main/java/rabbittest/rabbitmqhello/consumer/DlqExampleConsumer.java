package rabbittest.rabbitmqhello.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import rabbittest.rabbitmqhello.config.DeadLetterQueueConfig;
import rabbittest.rabbitmqhello.dto.ImageTaskDto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class DlqExampleConsumer {
    @RabbitListener(queues = DeadLetterQueueConfig.WORK_QUEUE)
    public void ProcessImageTask(ImageTaskDto taskDto) {
        log.info(taskDto.toString());
        if("error-image.jpg".equals(taskDto.getOriginalFileName())) {
            log.error("error-image.jpg");
            throw new AmqpRejectAndDontRequeueException("error-image.jpg");
        }

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = DeadLetterQueueConfig.DEAD_LETTER_QUEUE)
    public void monitorDeadLetter(
            Message deadLetterMessage,
            @Header(name = "x-death", required = false) List<Map<String, Object>> deathHeader
    ) {
        log.info("message body={}", new String(deadLetterMessage.getBody()));
        log.info("x-death={}", deathHeader);
    }

}
