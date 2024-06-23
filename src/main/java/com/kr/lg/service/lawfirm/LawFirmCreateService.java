package com.kr.lg.service.lawfirm;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.common.layer.LawFLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface LawFirmCreateService {

   @Transactional
   void applyLawFirm(LawFLayer LawFLayer) throws LgException;
   @Transactional(rollbackFor = Exception.class)
   boolean confirmLawFirm(LawFLayer LawFLayer) throws LgException;
   @Transactional
   void enrollLawFirm(LawFLayer LawFLayer) throws LgException;

}
