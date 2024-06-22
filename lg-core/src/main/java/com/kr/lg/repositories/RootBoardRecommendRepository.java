package com.kr.lg.repositories;


import com.kr.lg.entities.BoardRecommendTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootBoardRecommendRepository extends JpaRepository<BoardRecommendTb, Long> {

}
