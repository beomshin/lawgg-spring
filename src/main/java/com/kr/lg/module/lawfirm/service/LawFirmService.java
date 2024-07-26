package com.kr.lg.module.lawfirm.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.lawfirm.model.req.ApplyLawFirmRequest;
import com.kr.lg.module.lawfirm.exception.LawFirmException;

public interface LawFirmService {

    void applyLawFirm(ApplyLawFirmRequest request, UserTb userTb) throws LawFirmException;
}
