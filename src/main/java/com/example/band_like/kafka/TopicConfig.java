package com.example.band_like.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.FixedBackOff;

@Component
public class TopicConfig {
    public final static String album = "album";
    public final static String board = "board";
    public final static String memberDelete = "memberDelete";

    @Bean
    public NewTopic albumTopic() {
        return new NewTopic(album, 1, (short)1);
    }

    @Bean
    public NewTopic memberDeleteTopic() {
        return new NewTopic(memberDelete, 1, (short)1);
    }


    @Bean
    public NewTopic boardTopic() {
        return new NewTopic(board, 1, (short)1);
    }

    @Bean
    public RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }

    @Bean
    public CommonErrorHandler errorHandler(KafkaOperations<Object, Object> kafkaOperations) {
        return new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(kafkaOperations),
                new FixedBackOff(1000L, 2)
        );
    }
}
