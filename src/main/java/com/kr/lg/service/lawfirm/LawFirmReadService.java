package com.kr.lg.service.lawfirm;

import com.kr.lg.exception.LgException;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.model.common.layer.LawFLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface LawFirmReadService {

    DefaultResponse findAllLawFirmList(LawFLayer LawFLayer) throws LgException;
    DefaultResponse findAnonymousLawFirmDetail(LawFLayer LawFLayer) throws LgException;
    DefaultResponse findUserLawFirmDetail(LawFLayer LawFLayer) throws LgException;
    DefaultResponse findMyLawFirm(LawFLayer LawFLayer) throws LgException;
    DefaultResponse findApplyListMyLawFirm(LawFLayer LawFLayer) throws LgException;
    DefaultResponse findUserListMyLawFirm(LawFLayer LawFLayer) throws LgException;
}
