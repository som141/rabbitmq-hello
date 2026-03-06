package rabbittest.rabbitmqhello.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "example.exchange";
    public static final String QUEUE_NAME = "example.queue";
    public static final String ROUTING_KEY = "example.key.#";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public DirectExchange exchange(){
        return new DirectExchange((EXCHANGE_NAME));
    }

    @Bean
    public Queue queue(){
//        durable: true (기본값) - 서버가 재시작되더라도 큐는 유지(근데 내용값은 보장 못해줌)
        return new Queue(QUEUE_NAME);

    }

    @Bean
    public Binding binding(Queue queue,  DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}
