package com.example.band_like.kafka;

import com.example.band_like.domain.request.LikeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlbumProducer {
    private final KafkaTemplate<String, LikeRequest> kafkaTemplate;
    public void send(LikeRequest likeRequest) {
        kafkaTemplate.send(TopicConfig.album, likeRequest);
    }

}
