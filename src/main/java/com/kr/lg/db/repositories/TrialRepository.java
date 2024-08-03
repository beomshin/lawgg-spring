package com.kr.lg.db.repositories;

import com.kr.lg.common.enums.entity.status.TrialStatus;
import com.kr.lg.common.enums.entity.type.LiveType;
import com.kr.lg.common.enums.entity.type.TrialEndingType;
import com.kr.lg.db.entities.LawFirmTb;
import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.common.enums.entity.status.PrecedentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.sql.Timestamp;
import java.util.Optional;

public interface TrialRepository extends JpaRepository<TrialTb, Long> {

    Optional<TrialTb> findByTrialIdAndUserTb_UserId(@Param("trialId") long trialId, @Param("userId") long userId);

    @Query(value = "SELECT * FROM TrialTb b ORDER BY b.mainPostType desc, b.view desc LIMIT 1", nativeQuery = true)
    TrialTb findByMainPostType();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT b FROM TrialTb b where b.trialId = :trialId")
    TrialTb findLockTrial(@Param("trialId") Long trialId);
    @Modifying
    @Query(value = "UPDATE TrialTb SET playVideo = :playVideo, replay = :replay, status = :status WHERE trialId = :trialId")
    void uploadVideoAndReply(@Param("trialId") Long trialId, String playVideo, String replay, TrialStatus status);

    @Modifying
    @Query(value = "UPDATE TrialTb SET report = report + 1  WHERE trialId = :trialId")
    void reportTrial(@Param("trialId") Long trialId);

    @Modifying
    @Query(value = "UPDATE TrialTb SET view = view + 1  WHERE trialId = :trialId")
    void viewTrial(@Param("trialId") Long trialId);

    @Modifying
    @Query(value = "UPDATE TrialTb SET status = :status WHERE trialId = :trialId")
    void updateStatus(@Param("trialId") Long trialId, @Param("status") TrialStatus status);

    @Modifying
    @Query(value = "UPDATE TrialTb SET commentCount = commentCount + :count  WHERE trialId = :trialId")
    void updateCommentCount(@Param("trialId") Long trialId, Long count);

    @Modifying
    @Query(value = "UPDATE TrialTb SET recommendCount = recommendCount + :count  WHERE trialId = :trialId")
    void updateRecommendCount(@Param("trialId") Long trialId, Long count);

    @Modifying
    @Query(value = "UPDATE TrialTb SET lawFirmTb = :lawFirmTb, judge = :judge, url = :url, liveType = :liveType, liveDt = :liveDt  WHERE trialId = :trialId")
    void updateLive(@Param("trialId") Long trialId, LawFirmTb lawFirmTb, UserTb judge, String url, LiveType liveType, Timestamp liveDt);

    @Modifying
    @Query(value = "UPDATE TrialTb SET precedent = :precedent, endingType = :endingType, endDt = :endDt  WHERE trialId = :trialId")
    void updateEnd(@Param("trialId") Long trialId, PrecedentStatus precedent, TrialEndingType endingType, Timestamp endDt);

}
