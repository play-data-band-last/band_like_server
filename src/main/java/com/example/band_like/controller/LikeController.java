package com.example.band_like.controller;

import com.example.band_like.domain.request.LikeRequest;
import com.example.band_like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    public void likeCount(@RequestBody LikeRequest request) {
        likeService.likeCount(request);
    }
}
