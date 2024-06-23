package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.MainTrialTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootMainTrialRepository extends JpaRepository<MainTrialTb, Long> {
}
