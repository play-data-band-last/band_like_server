package com.example.band_like.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity @Setter
@Builder @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "likes",
        indexes = @Index(name = "idx_target_member", columnList = "targetId,memberId")
)
public class Like {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID targetId;
    private Long memberId;
}
