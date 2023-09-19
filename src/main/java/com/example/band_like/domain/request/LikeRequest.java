package com.example.band_like.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class LikeRequest {
    private String target;
    private UUID targetId;
    private Long memberId;
}
