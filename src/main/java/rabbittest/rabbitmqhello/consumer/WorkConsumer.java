package rabbittest.rabbitmqhello.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import rabbittest.rabbitmqhello.config.RabbitMQConfig;
import rabbittest.rabbitmqhello.dto.ImageTaskDto;

@Slf4j
@Component
public class WorkConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void processImageTask(ImageTaskDto task) throws InterruptedException{
        log.info("Worker [{}] started processing task: {}",Thread.currentThread().getName(),task);

        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            log.error("Task processing wsa interrupted for task ID: {}",task.getTaskId(),e);

            throw e;
        }
        log.info("Worker [{}] finished processing task: {}",Thread.currentThread().getName(),task.getTaskId());

    }

}
