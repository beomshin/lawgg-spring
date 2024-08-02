package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.NickNameTb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NickNameRepository extends JpaRepository<NickNameTb, Long> {
    Optional<NickNameTb> findByName(String nickName);
}
