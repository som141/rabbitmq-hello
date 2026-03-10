package rabbittest.rabbitmqhello.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadLetterQueueConfig {
//    1. workingqueue
    public final static String WORK_EXCHAGE = "work.exchange";
    public final static String WORK_QUEUE = "work.queue";
    public final static String WORK_ROUTING_KEY = "work.key";
//    2. deadQueue
    public final static String DEAD_LETTER_EXCHAGE = "dead.letter.exchange";
    public final static String DEAD_LETTER_QUEUE = "dead.letter.queue";
    public final static String DEAD_LETTER_ROUTING_KEY = "dead.letter.key";

    @Bean
    public DirectExchange workExchange() {
        return new DirectExchange(WORK_EXCHAGE);
    }

    @Bean
    public Queue workQueue() {
        return  QueueBuilder.durable(WORK_QUEUE).
                withArgument("x-dead-letter-exchange",DEAD_LETTER_EXCHAGE).
                withArgument("x-dead-letter-routing-key",DEAD_LETTER_ROUTING_KEY).
                build();
    }

    @Bean
    public Binding workBinding(Queue workQueue, DirectExchange workExchange) {
        return BindingBuilder.bind(workQueue).to(workExchange).with(WORK_ROUTING_KEY);
    }

//     dead letter component

    @Bean
    public FanoutExchange deadLetterExchange() {
        return new FanoutExchange(DEAD_LETTER_EXCHAGE);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DEAD_LETTER_QUEUE);
    }

    @Bean
    public Binding deadLetterBinding(FanoutExchange deadLetterExchange, Queue deadLetterQueue) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange);
    }
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }



}
