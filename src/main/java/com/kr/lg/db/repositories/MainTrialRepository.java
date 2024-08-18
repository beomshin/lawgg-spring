package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.MainTrialTb;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainTrialRepository extends JpaRepository<MainTrialTb, Long> {

    @EntityGraph(attributePaths = {"userTb"})
    List<MainTrialTb> findAll();
}
