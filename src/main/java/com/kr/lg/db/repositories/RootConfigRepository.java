package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.ConfigTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RootConfigRepository extends JpaRepository<ConfigTb, Long> {

    @Query("SELECT c FROM ConfigTb c WHERE c.key IN (:keys)")
    List<ConfigTb> findConfig(@Param("keys") List<String> keys);

    ConfigTb findByKey(@Param("key") String key);

}

