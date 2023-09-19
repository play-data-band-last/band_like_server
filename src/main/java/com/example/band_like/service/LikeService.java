package com.example.band_like.service;

import com.example.band_like.api.AlbumClient;
import com.example.band_like.api.BoardClient;
import com.example.band_like.domain.entity.Like;
import com.example.band_like.domain.request.LikeRequest;
import com.example.band_like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final BoardClient boardClient;
    private final AlbumClient albumClient;

      //'좋아요'
    @Transactional
      public void likeCount(LikeRequest likeRequest) {
          Optional<Like> like = likeRepository.findByTargetIdAndMemberId(likeRequest.getTargetId(), likeRequest.getMemberId());
          String target = likeRequest.getTarget();
          if (like.isPresent()) {
              likeRepository.delete(like.get());
              if (target.equals("board")) {
                  boardClient.updateLikeCount(likeRequest.getTargetId(), - 1);
              } else {
                  albumClient.updateLikeCount(likeRequest.getTargetId(), - 1);
              }
        } else {
              likeRepository.save(Like.builder().targetId(likeRequest.getTargetId()).memberId(likeRequest.getMemberId()).build());
              if (target.equals("board")) {
                  boardClient.updateLikeCount(likeRequest.getTargetId(), 1);
              } else {
                  albumClient.updateLikeCount(likeRequest.getTargetId(),  1);
              }
        }
    }

}
