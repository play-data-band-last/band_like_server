package com.example.band_like.kafka;

import com.example.band_like.domain.request.LikeRequest;
import com.example.band_like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaListner {

    private final LikeService likeService;

    @RetryableTopic
    @KafkaListener(topics = TopicConfig.memberDelete)
    public void memberDeleteListener(LikeRequest likeRequest) {
        likeService.memberDeleteHandler(likeRequest.getMemberId());
    }


    @DltHandler
    public void processDltMessage(String dltMessage) {
        // DLT 토픽에서 메시지를 처리합니다. (예: 로깅 또는 추가 조사)
        System.out.println("DLT에서 메시지 수신: " + dltMessage);
    }

}
