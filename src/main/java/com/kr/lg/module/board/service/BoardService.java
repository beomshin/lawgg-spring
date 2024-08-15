package com.kr.lg.module.board.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.board.model.req.*;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.entry.BoardEntry;
import org.springframework.data.domain.Page;

public interface BoardService {

    Page<BoardEntry> findBoards(FindPositionRequest request) throws BoardException;
    Page<BoardEntry> findMyBoards(FindMyBoardRequest request, UserTb userTb) throws BoardException;
    Page<BoardEntry> findLawFirmBoards(FindLawFirmBoardRequest request) throws BoardException;
    BoardEntry findBoard(long boardId, UserTb userTb) throws BoardException;
    BoardEntry findBoardWithNotLogin(long boardId) throws BoardException;
    BoardEntry findBoardWithLogin(long boardId, UserTb userTb) throws BoardException;
    void enrollBoardWithNotLogin(EnrollPositionRequest request, String ip) throws BoardException;
    void enrollBoardWithLogin(EnrollPositionRequest request, String ip, UserTb userTb) throws BoardException;
    void enrollBoardWithLawFirmLogin(EnrollBoardWithLawFirmLoginRequest request, String ip, UserTb userTb) throws BoardException;
    void updateBoardWithNotLogin(UpdatePositionRequest request) throws BoardException;
    void updateBoardWithLogin(UpdatePositionRequest request, UserTb userTb) throws BoardException;
    void deleteBoardWithNotLogin(DeletePositionRequest request) throws BoardException;
    void deleteBoardWithLogin(DeletePositionRequest request, UserTb userTb) throws BoardException;
    void reportBoard(ReportBoardRequest request, String ip) throws BoardException;
    void recommendBoard(RecommendBoardRequest request, UserTb userTb) throws BoardException;
    void deleteRecommendBoard(DeleteRecommendBoardRequest request, UserTb userTb) throws BoardException;
    void loginBoardWithNotLogin(LoginBoardWithNotLoginRequest request) throws BoardException;
    void loginBoardWithLogin(LoginBoardWithLoginRequest request, UserTb userTb) throws BoardException;

}
