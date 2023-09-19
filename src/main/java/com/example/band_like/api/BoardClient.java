package com.example.band_like.api;

import com.example.band_like.domain.request.LikeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient("BAND-BOARD-SERVICE")
public interface BoardClient {
    @PutMapping("/api/v1/board/likeCountUpdate/{boardId}")
    void updateLikeCount(@PathVariable("boardId") UUID boardId, @RequestBody Integer count);
}
