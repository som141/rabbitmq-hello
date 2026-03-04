package rabbittest.rabbitmqhello.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "hello.exchange";
    public static final String QUEUE_NAME = "hello.queue";
    public static final String ROUTING_KEY = "hello.key.#";

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange((EXCHANGE_NAME));
    }

    @Bean
    public Queue queue(){
//        durable: true (기본값) - 서버가 재시작되더라도 큐는 유지(근데 내용값은 보장 못해줌)
        return new Queue(QUEUE_NAME);

    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}
