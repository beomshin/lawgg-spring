package com.kr.lg.module.board.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.board.model.req.ReportBoardRequest;
import com.kr.lg.module.board.model.req.DeleteBoardWithNotLoginRequest;
import com.kr.lg.module.board.model.req.DeleteBoardWithLoginRequest;
import com.kr.lg.module.board.model.req.UpdateBoardWithNotLoginRequest;
import com.kr.lg.module.board.model.req.UpdateBoardWithLoginRequest;
import com.kr.lg.module.board.model.req.EnrollBoardWithNotLoginRequest;
import com.kr.lg.module.board.model.req.EnrollBoardWithLawFirmLoginRequest;
import com.kr.lg.module.board.model.req.EnrollBoardWithLoginRequest;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.entry.BoardEntry;
import com.kr.lg.module.board.model.req.FindBoardRequest;
import com.kr.lg.module.board.model.req.FindLawFirmBoardRequest;
import com.kr.lg.module.board.model.req.FindMyBoardRequest;
import org.springframework.data.domain.Page;

public interface BoardService {

    Page<BoardEntry> findBoards(FindBoardRequest request) throws BoardException;
    Page<BoardEntry> findMyBoards(FindMyBoardRequest request, UserTb userTb) throws BoardException;
    Page<BoardEntry> findLawFirmBoards(FindLawFirmBoardRequest request) throws BoardException;
    BoardEntry findBoard(long boardId) throws BoardException;
    BoardEntry findBoard(long boardId, UserTb userTb) throws BoardException;
    void enrollBoardWithNotLogin(EnrollBoardWithNotLoginRequest request, String ip) throws BoardException;
    void enrollBoardWithLogin(EnrollBoardWithLoginRequest request, String ip, UserTb userTb) throws BoardException;
    void enrollBoardWithLawFirmLogin(EnrollBoardWithLawFirmLoginRequest request, String ip, UserTb userTb) throws BoardException;
    void updateBoardWithNotLogin(UpdateBoardWithNotLoginRequest request) throws BoardException;
    void updateBoardWithLogin(UpdateBoardWithLoginRequest request, UserTb userTb) throws BoardException;
    void deleteBoardWithNotLogin(DeleteBoardWithNotLoginRequest request) throws BoardException;
    void deleteBoardWithLogin(DeleteBoardWithLoginRequest request, UserTb userTb) throws BoardException;
    void reportBoard(ReportBoardRequest request, String ip) throws BoardException;

}
