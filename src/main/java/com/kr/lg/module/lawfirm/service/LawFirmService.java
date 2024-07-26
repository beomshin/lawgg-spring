package com.kr.lg.module.lawfirm.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.lawfirm.model.entry.LawFirmEntry;
import com.kr.lg.module.lawfirm.model.req.ApplyLawFirmRequest;
import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.model.req.CancelApplyLawFirmRequest;
import com.kr.lg.module.lawfirm.model.req.FindLawFirmsRequest;
import com.kr.lg.module.lawfirm.model.req.QuitLawFirmRequest;
import org.springframework.data.domain.Page;

public interface LawFirmService {

    void applyLawFirm(ApplyLawFirmRequest request, UserTb userTb) throws LawFirmException;
    void quitLawFirm(QuitLawFirmRequest request, UserTb userTb) throws LawFirmException;
    void cancelApplyLawFirm(CancelApplyLawFirmRequest request, UserTb userTb) throws LawFirmException;
    Page<LawFirmEntry> findLawFirms(FindLawFirmsRequest request) throws LawFirmException;
    LawFirmEntry findLawFirmWithNotLogin(long id) throws LawFirmException;
    LawFirmEntry findLawFirmWithLogin(long id, UserTb userTb) throws LawFirmException;
}
