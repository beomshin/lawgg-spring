package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.common.enums.entity.flag.AuthFlag;
import com.kr.lg.common.enums.entity.type.SnsType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserTb, Long> {

    @EntityGraph(attributePaths = {"tierTb", "lawFirmTb"})
    Optional<UserTb> findByLoginId(String loginId);
    int countByLoginId(String loginId);
    @EntityGraph(attributePaths = {"tierTb", "lawFirmTb"})
    Optional<UserTb> findByUserId(Long userId);
    Optional<UserTb> findByNickName(String nickName);
    Optional<UserTb> findBySnsIdAndSnsType(@Param("snsId") String snsId, @Param("snsType") SnsType snsType);
    List<UserTb> findByEmail(String email);
    Optional<UserTb> findByEmailAndLoginId(String email, String loginId);
    Optional<UserTb> findByEmailAndAndUserId(String email, long userId);
    @Modifying
    @Query(value = "UPDATE UserTb SET password = :password  WHERE userId = :userId")
    void updatePassword(@Param("userId") Long userId, String password);
    @Modifying
    @Query(value = "UPDATE UserTb SET password = :password, nickName = :nickName, email = :email, hashEmail = :hashEmail  WHERE userId = :userId")
    void updateUserInfo(@Param("userId") Long userId, String password, String nickName, String email, String hashEmail);
    @Modifying
    @Query(value = "UPDATE UserTb SET lawFirmTb = null, lawFirmEnrollDt = null  WHERE userId = :userId")
    void deleteLawFirm(@Param("userId") Long userId);
    @Modifying
    @Query(value = "UPDATE UserTb ut SET ut.ci = :ci, ut.di = :di, ut.name = :name, ut.gender = :gender, ut.birth = :birthday, ut.authFlag = :authFlag, ut.authDt = current_timestamp  WHERE ut.userId = :userId")
    void updateAuth(@Param("ci") String ci, @Param("di") String di, @Param("name") String name, @Param("gender") String gender, @Param("birthday") String birthday, @Param("authFlag") AuthFlag authFlag, @Param("userId") Long userId);
    @Modifying
    @Query(value = "UPDATE UserTb SET commentCount = commentCount + :count  WHERE userId = :userId")
    void updateCommentCount(@Param("userId") Long userId, Long count);
    @Modifying
    @Query(value = "UPDATE UserTb SET boardCount = boardCount + :count  WHERE userId = :userId")
    void updateBoardCount(@Param("userId") Long userId, Long count);
    @Modifying
    @Query(value = "UPDATE UserTb SET trialCount = trialCount + :count  WHERE userId = :userId")
    void updateTrialCount(@Param("userId") Long userId, Long count);
    @Modifying
    @Query(value = "UPDATE UserTb SET profile = :profile  WHERE userId = :userId")
    void updateProfile(@Param("userId") Long userId, String profile);

}
