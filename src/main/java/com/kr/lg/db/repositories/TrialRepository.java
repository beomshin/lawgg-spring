package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.LawFirmTb;
import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.enums.EndingEnum;
import com.kr.lg.enums.LiveEnum;
import com.kr.lg.enums.entity.element.MainPostTypeEnum;
import com.kr.lg.enums.PrecedentEnum;
import com.kr.lg.enums.StatusEnum;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.sql.Timestamp;
import java.util.Optional;

public interface TrialRepository extends RootTrialRepository {

    TrialTb findByTrialIdAndUserTb(Long trialId, UserTb userTb);

    @Query(value = "SELECT * FROM TrialTb b ORDER BY b.mainPostType desc, b.view desc LIMIT 1", nativeQuery = true)
    TrialTb findByMainPostType(MainPostTypeEnum mainPostType);

    long countByTrialIdAndUserTb(Long trialId, UserTb userTb);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT b FROM TrialTb b where b.trialId = :trialId")
    TrialTb findLockTrial(@Param("trialId") Long trialId);

    @Query(value = "SELECT * FROM TrialTb ORDER BY view DESC LIMIT 1", nativeQuery = true)
    Optional<TrialTb> findTopTrial();
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialTb SET report = report + 1  WHERE trialId = :trialId")
    int reportTrial(@Param("trialId") Long trialId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialTb SET view = view + 1  WHERE trialId = :trialId")
    int viewTrial(@Param("trialId") Long trialId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialTb SET status = :status WHERE trialId = :trialId")
    int updateStatus(@Param("trialId") Long trialId, @Param("status") StatusEnum status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialTb SET subheading = :subheading, plaintiffOpinion = :plaintiffOpinion, defendantOpinion = :defendantOpinion, content = :content WHERE trialId = :trialId")
    int updateTrial(@Param("trialId") Long trialId, String subheading, String plaintiffOpinion, String defendantOpinion, String content);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialTb SET playVideo = :playVideo, replay = :replay, status = :status WHERE trialId = :trialId")
    int updateTrial(@Param("trialId") Long trialId, String playVideo, String replay, StatusEnum status);

    @Modifying
    @Query(value = "UPDATE TrialTb SET commentCount = commentCount + :count  WHERE trialId = :trialId")
    int updateCommentCount(@Param("trialId") Long trialId, Long count);

    @Modifying
    @Query(value = "UPDATE TrialTb SET recommendCount = recommendCount + :count  WHERE trialId = :trialId")
    int updateRecommendCount(@Param("trialId") Long trialId, Long count);

    @Modifying
    @Query(value = "UPDATE TrialTb SET lawFirmTb = :lawFirmTb, judge = :judge, url = :url, liveType = :liveType, liveDt = :liveDt  WHERE trialId = :trialId")
    int updateLive(@Param("trialId") Long trialId, LawFirmTb lawFirmTb, UserTb judge, String url, LiveEnum liveType, Timestamp liveDt);

    @Modifying
    @Query(value = "UPDATE TrialTb SET precedent = :precedent, endingType = :endingType, endDt = :endDt  WHERE trialId = :trialId")
    int updateEnd(@Param("trialId") Long trialId, PrecedentEnum precedent, EndingEnum endingType, Timestamp endDt);

}
