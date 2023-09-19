package com.example.band_like.repository;


import com.example.band_like.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByTargetIdAndMemberId(UUID targetId, Long memberId);

}
