package com.kr.lg.module.user.mapper;

import com.kr.lg.module.user.model.mapper.UBoardMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UBoardMapper> findUserBoards(Long userId, Integer limit, Integer offset, String keyword);
    Integer findUserBoardsCount(Long userId, String keyword);
}
