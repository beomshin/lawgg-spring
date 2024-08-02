package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.TrialAttachTb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrialAttachRepository extends JpaRepository<TrialAttachTb, Long> {
    List<TrialAttachTb> findByTrialTb_TrialId(long trialId);
}
