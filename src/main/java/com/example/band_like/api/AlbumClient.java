package com.example.band_like.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient("BAND-ALBUM-SERVICE")
public interface AlbumClient {
    @PutMapping("/api/v1/album/likeCountUpdate/{albumId}")
    public void updateLikeCount(@PathVariable("albumId") UUID albumId,
                                @RequestBody Integer count);
}

