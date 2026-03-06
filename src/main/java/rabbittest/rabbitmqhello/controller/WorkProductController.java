package rabbittest.rabbitmqhello.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rabbittest.rabbitmqhello.config.RabbitMQConfig;
import rabbittest.rabbitmqhello.dto.ImageTaskDto;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WorkProductController {
    private final RabbitTemplate rabbitTemplate;

    @PostMapping("work/request-image-resize")
    public void requestImageResize(@RequestParam String fileName){
        log.info("request = "+fileName);

        for(int i=0;i<3;i++){
            String id = UUID.randomUUID().toString();
            int width = 1920/(i+1);
            int height = 1080/(i+1);

            ImageTaskDto imageTaskDto = new ImageTaskDto(
                    id,fileName,width,height
            );

            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_NAME,
                    RabbitMQConfig.ROUTING_KEY
            )
        }
    }

}
