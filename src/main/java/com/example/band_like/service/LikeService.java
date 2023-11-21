package com.example.band_like.service;

import com.example.band_like.domain.entity.Like;
import com.example.band_like.domain.request.LikeCountUpdateRequest;
import com.example.band_like.domain.request.LikeRequest;
import com.example.band_like.kafka.AlbumProducer;
import com.example.band_like.kafka.BoardProducer;
import com.example.band_like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
//    private final BoardClient boardClient;
//    private final AlbumClient albumClient;
    private final AlbumProducer albumProducer;
    private final BoardProducer boardProducer;

      //'좋아요'
    @Transactional
      public void likeCount(LikeRequest likeRequest) {
          Optional<Like> like = likeRepository.findByTargetIdAndMemberId(likeRequest.getTargetId(), likeRequest.getMemberId());
          String target = likeRequest.getTarget();
          if (like.isPresent()) {
              likeRepository.delete(like.get());

              LikeCountUpdateRequest likeCountUpdateRequest = LikeCountUpdateRequest.builder()
                      .count(-1)
                      .boardId(likeRequest.getTargetId())
                      .build();

              if (target.equals("board")) {
                    boardProducer.send(likeCountUpdateRequest);
                  // boardClient.updateLikeCount(likeRequest.getTargetId(), - 1);
              } else {
                  albumProducer.send(likeCountUpdateRequest);
                 // albumClient.updateLikeCount(likeRequest.getTargetId(), - 1);
              }
        } else {
              likeRepository.save(Like.builder().targetId(likeRequest.getTargetId()).memberId(likeRequest.getMemberId()).build());

              LikeCountUpdateRequest likeCountUpdateRequest = LikeCountUpdateRequest.builder()
                      .count(1)
                      .boardId(likeRequest.getTargetId())
                      .build();

              if (target.equals("board")) {
                  boardProducer.send(likeCountUpdateRequest);
                  //boardClient.updateLikeCount(likeRequest.getTargetId(), 1);
              } else {
                  albumProducer.send(likeCountUpdateRequest);
                  //albumClient.updateLikeCount(likeRequest.getTargetId(),  1);
              }
        }
    }

}
