package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.TierTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TierRepository extends JpaRepository<TierTb, Long> {
    TierTb findByKey(String key);
}
