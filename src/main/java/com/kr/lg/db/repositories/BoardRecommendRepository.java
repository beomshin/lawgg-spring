package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.BoardRecommendTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRecommendRepository extends JpaRepository<BoardRecommendTb, Long> {
    Optional<BoardRecommendTb> findByBoardTb_BoardIdAndUserTb_UserId(long boardId, long userId);
    @Modifying
    @Query(value = "DELETE FROM BoardRecommendTb where boardTb.boardId = :boardId and userTb.userId = :userId")
    void deleteRecommendBoard(@Param("boardId") long boardId, @Param("userId") long userId);

}
