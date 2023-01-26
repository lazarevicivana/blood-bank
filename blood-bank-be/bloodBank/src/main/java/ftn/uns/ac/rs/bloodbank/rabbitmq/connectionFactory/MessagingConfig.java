package ftn.uns.ac.rs.bloodbank.rabbitmq.connectionFactory;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MessagingConfig {
    @Value("${QUEUE_LOCATION}")
    public  String QUEUE_LOCATION;
    @Value("${QUEUE_HOSPITAL}")
    public  String QUEUE_HOSPITAL;
    @Value("${QUEUE_HOSPITAL_1}")
    public  String QUEUE_HOSPITAL1;
    @Value("${EXCHANGE}")
    public   String EXCHANGE ;
    @Value("${ROUTING_KEY_HOSPITAL}")
    public   String ROUTING_KEY_HOSPITAL;
    @Value("${ROUTING_KEY_HOSPITAL_1}")
    public   String ROUTING_KEY_HOSPITAL1;

    @Value("${ROUTING_KEY_LOCATION}")
    public   String ROUTING_KEY_LOCATION;
    public static String QUEUE_STATIC_L;
    public static String QUEUE_STATIC_H;
    public static String QUEUE_STATIC_H1;
    public static String EXCHANGE_STATIC;
    public static String ROUTING_KEY_STATIC_H;
    public static String ROUTING_KEY_STATIC_H1;
    public static String ROUTING_KEY_STATIC_L;

    @PostConstruct
    public void init(){
        EXCHANGE_STATIC = EXCHANGE;
        QUEUE_STATIC_H = QUEUE_HOSPITAL;
        QUEUE_STATIC_H1 = QUEUE_HOSPITAL1;
        QUEUE_STATIC_L = QUEUE_LOCATION;
        ROUTING_KEY_STATIC_H = ROUTING_KEY_HOSPITAL;
        ROUTING_KEY_STATIC_L = ROUTING_KEY_LOCATION;
        ROUTING_KEY_STATIC_H1 = ROUTING_KEY_HOSPITAL1;
    }

    @Bean("queue1")
    public Queue queue1(){
        return new Queue(QUEUE_HOSPITAL);
    }
    @Bean("queue2")
    public Queue queue2(){
        return new Queue(QUEUE_LOCATION);
    }
    @Bean("queue3")
    public Queue queue3(){
        return new Queue(QUEUE_HOSPITAL1);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_STATIC);
    }
    @Bean
    public Binding binding1(TopicExchange exchange){
        return BindingBuilder.bind(queue1()).to(exchange).with(ROUTING_KEY_STATIC_H);
    }
    @Bean
    public Binding binding2(TopicExchange exchange){
        return BindingBuilder.bind(queue2()).to(exchange).with(ROUTING_KEY_STATIC_L);
    }
    @Bean
    public Binding binding3(TopicExchange exchange){
        return BindingBuilder.bind(queue3()).to(exchange).with(ROUTING_KEY_STATIC_H1);
    }
//    @Bean
//    public MessageConverter converter(){
//        return  new Jackson2JsonMessageConverter();
//    }
//    @Bean
//    public AmqpTemplate template(ConnectionFactory connectionFactory){
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(converter());
//        return rabbitTemplate;
//    }
}
