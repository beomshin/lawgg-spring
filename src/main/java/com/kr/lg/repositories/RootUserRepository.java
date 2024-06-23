package com.kr.lg.repositories;

import com.kr.lg.entities.UserTb;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RootUserRepository extends JpaRepository<UserTb, Long> {

    Optional<UserTb> findByLoginId(String loginId);

    @EntityGraph(attributePaths = {"tierId", "lawFirmId"})
    Optional<UserTb> findByUserId(Long userId);
}
