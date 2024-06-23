package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.BoardRecommendTb;
import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.UserTb;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BoardRecommendRepository extends RootBoardRecommendRepository {

    Optional<BoardRecommendTb> findByBoardTb_BoardIdAndUserTb(Long boardId, UserTb userTb);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM BoardRecommendTb where boardTb = :boardTb and userTb = :userTb")
    int deleteRecommendBoard(@Param("boardTb") BoardTb boardTb, @Param("userTb") UserTb userTb);


    long countByBoardTb_BoardIdAndUserTb(Long boardId, UserTb userTb);
}
