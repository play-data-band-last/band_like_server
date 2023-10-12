package com.example.band_like.repository;


import com.example.band_like.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByTargetIdAndMemberId(UUID targetId, Long memberId);

    @Modifying
    @Query("DELETE FROM Like l " +
            "where l.memberId = :userId")
    void DeleteMember(@Param("userId") Long userId);

}
