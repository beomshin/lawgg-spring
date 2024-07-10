package com.kr.lg.db.repositories;


import com.kr.lg.db.entities.BoardRecommendTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootBoardRecommendRepository extends JpaRepository<BoardRecommendTb, Long> {

}
