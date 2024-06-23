package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.LawFirmTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.common.enums.entity.element.AuthEnum;
import com.kr.lg.common.enums.entity.element.SnsEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

public interface UserRepository extends RootUserRepository {
    Optional<UserTb> findByLoginId(String loginId);
    Optional<UserTb> findByNickName(String nickName);
    Optional<UserTb> findByLoginIdAndCi(String loginId, String ci);
    Optional<UserTb> findBySnsIdAndSnsType(@Param("snsId") String snsId, @Param("snsType") SnsEnum snsType);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserTb SET lawFirmId = null, lawFirmEnrollDt = null  WHERE userId = :userId")
    int deleteLawFirm(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserTb SET lawFirmId = :lawFirmId, lawFirmEnrollDt = :lawFirmEnrollDt  WHERE userId = :userId")
    int updateLawFirm(@Param("userId") Long userId, @Param("lawFirmId")LawFirmTb lawFirmId, @Param("lawFirmEnrollDt") Timestamp lawFirmEnrollDt);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserTb ut SET ut.ci = :ci, ut.di = :di, ut.name = :name, ut.gender = :gender, ut.birth = :birthday, ut.authFlag = :authFlag, ut.authDt = current_timestamp  WHERE ut.userId = :userId")
    void updateAuth(@Param("ci") String ci, @Param("di") String di, @Param("name") String name, @Param("gender") String gender, @Param("birthday") String birthday, @Param("authFlag") AuthEnum authFlag, @Param("userId") Long userId);

    @Modifying
    @Query(value = "UPDATE UserTb SET commentCount = commentCount + :count  WHERE userId = :userId")
    int updateCommentCount(@Param("userId") Long userId, Long count);

    @Modifying
    @Query(value = "UPDATE UserTb SET boardCount = boardCount + :count  WHERE userId = :userId")
    int updateBoardCount(@Param("userId") Long userId, Long count);

    @Modifying
    @Query(value = "UPDATE UserTb SET trialCount = trialCount + :count  WHERE userId = :userId")
    int updateTrialCount(@Param("userId") Long userId, Long count);

    @Modifying
    @Query(value = "UPDATE UserTb SET password = :password  WHERE userId = :userId")
    int updatePassword(@Param("userId") Long userId, String password);

    @Modifying
    @Query(value = "UPDATE UserTb SET password = :password, nickName = :nickName, email = :email, hashEmail = :hashEmail  WHERE userId = :userId")
    int updateUser(@Param("userId") Long userId, String password, String nickName, String email, String hashEmail);

    @Modifying
    @Query(value = "UPDATE UserTb SET profile = :profile  WHERE userId = :userId")
    int updateProfile(@Param("userId") Long userId, String profile);
}
