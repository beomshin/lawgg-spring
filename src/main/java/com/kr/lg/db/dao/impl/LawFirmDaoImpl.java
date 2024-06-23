package com.kr.lg.db.dao.impl;

import com.kr.lg.web.common.layer.LawFLayer;
import com.kr.lg.web.querydsl.LawFirmQ;
import com.kr.lg.db.dao.LawFirmDao;
import com.kr.lg.query.LawFirmQuery;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LawFirmDaoImpl implements LawFirmDao {

    private final LawFirmQuery lawFirmQuery;

    @Override
    public Page<LawFirmQ> findAllLawFirmList(LawFLayer requestDto, Pageable pageable) {
        JPAQuery<LawFirmQ> content = lawFirmQuery.findAllLawFirmList(requestDto, pageable); // 게시판 content
        JPAQuery<Long> count = lawFirmQuery.findAllLawFirmListCount(requestDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public Optional<LawFirmQ> findLawFirmDetail(LawFLayer requestDto) {
        return Optional.ofNullable(lawFirmQuery.findLawFirmDetail(requestDto).fetchOne());
    }

    @Override
    public Long findApply(LawFLayer requestDto) {
        return lawFirmQuery.findApply(requestDto).fetchOne();
    }

    @Override
    public Optional<LawFirmQ> findMyLawFirm(Long id) {
        return Optional.ofNullable(lawFirmQuery.findMyLawFirm(id).fetchOne());
    }

    @Override
    public Page<LawFirmQ> findApplyListMyLawFirm(LawFLayer requestDto, Pageable pageable) {
        JPAQuery<LawFirmQ> content = lawFirmQuery.findApplyListMyLawFirm(requestDto, pageable);
        JPAQuery<Long> count = lawFirmQuery.findApplyListMyLawFirmtCount(requestDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public Page<LawFirmQ> findUserListMyLawFirm(LawFLayer requestDto, Pageable pageable) {
        JPAQuery<LawFirmQ> content = lawFirmQuery.findUserListMyLawFirm(requestDto, pageable);
        JPAQuery<Long> count = lawFirmQuery.findUserListMyLawFirmCount(requestDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }
}
