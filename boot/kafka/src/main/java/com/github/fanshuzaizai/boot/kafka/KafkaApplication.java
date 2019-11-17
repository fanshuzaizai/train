package com.github.fanshuzaizai.boot.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
public class KafkaApplication {

    @Autowired
    private KafkaTemplate<String, String> template;


    @Autowired
    private TransactionTemplate transactionTemplate;

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args).close();
    }


//    @Bean
//    @ConditionalOnMissingBean(KafkaTemplate.class)
//    public KafkaTemplate<?, ?> kafkaTemplate(
//            ProducerFactory<Object, Object> kafkaProducerFactory,
//            ProducerListener<Object, Object> kafkaProducerListener) {
//        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<>(
//                kafkaProducerFactory);
//        if (this.messageConverter != null) {
//            kafkaTemplate.setMessageConverter(this.messageConverter);
//        }
//        kafkaTemplate.setProducerListener(kafkaProducerListener);
//        return kafkaTemplate;
//    }

    @Bean
    public ProducerListener<Object, Object> kafkaProducerListener() {
        return new ProducerListener<Object, Object>() {
            @Override
            public void onSuccess(String topic, Integer partition, Object key, Object value, RecordMetadata recordMetadata) {
                System.out.println("success........");
            }
        };
    }

    private final CountDownLatch latch = new CountDownLatch(3);

//    @Override
    public void run(String... args) throws Exception {
        ListenableFuture<SendResult<String, String>> send = this.template.send("myTopic", "foo1");
        System.out.println("[----]=" + send.get());
        this.template.send("myTopic", "foo2");
        this.template.send("myTopic", "foo3");
        latch.await(60, TimeUnit.SECONDS);
        log.info("All received");
    }

    @KafkaListener(topics = "myTopic")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        log.info("====收到信息：{}", cr.toString());
        latch.countDown();
    }

}
