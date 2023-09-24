package com.example.band_like.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class LikeCountUpdateRequest {
    private UUID boardId;
    private Integer count;
}
