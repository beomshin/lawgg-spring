package com.kr.lg.service.lawfirm.impl;

import com.kr.lg.db.dao.LawFirmDao;
import com.kr.lg.db.entities.LawFirmApplyTb;
import com.kr.lg.db.entities.LawFirmTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.common.enums.common.element.AcceptEnum;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.common.enums.entity.element.ApplyStatusEnum;
import com.kr.lg.db.repositories.LawFirmApplyRepository;
import com.kr.lg.db.repositories.LawFirmRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.model.common.global.GlobalCode;
import com.kr.lg.common.utils.AwsS3Utils;
import com.kr.lg.common.utils.LawFirmUtils;
import com.kr.lg.model.common.layer.LawFLayer;
import com.kr.lg.service.lawfirm.LawFirmCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@Slf4j
@RequiredArgsConstructor
public class LawFirmCreateServiceImpl implements LawFirmCreateService {

    private final LawFirmDao lawFirmDao;
    private final LawFirmApplyRepository lawFirmApplyRepository;
    private final LawFirmRepository lawFirmRepository;
    private final UserRepository userRepository;
    private final AwsS3Utils awsS3Utils;

    private final LawFirmUtils lawFirmUtils;

    @Override
    public void applyLawFirm(LawFLayer LawFLayer) throws LgException {
        if (LawFLayer.getUserTb().getLawFirmId() != null) throw new LgException(GlobalCode.ALREADY_ENROLL_LAW_FIRM); // 이미 로펌을 가지고 있는 경우
        else if (lawFirmDao.findApply(LawFLayer) > 0) throw new LgException(GlobalCode.ALREADY_APPLY_LAW_FIRM); // 이미 로펌 신청한 경우
        lawFirmApplyRepository.save(LawFirmApplyTb.builder()
                        .lawFirmTb(LawFirmTb.builder().lawFirmId(LawFLayer.getId()).build())
                        .userTb(LawFLayer.getUserTb())
                        .title(LawFLayer.getTitle())
                        .introduction(LawFLayer.getIntroduction())
                .build()); // 로펌 신청
    }

    @Override
    public boolean confirmLawFirm(LawFLayer LawFLayer) throws LgException {

        LawFirmApplyTb lawFirmApplyTb = lawFirmApplyRepository.findById(LawFLayer.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_APPLY_INFO));
        lawFirmUtils.isLawFirmManager(lawFirmApplyTb.getLawFirmTb().getUserTb(), LawFLayer.getUserTb());

        if (lawFirmApplyTb.getStatus().equals(ApplyStatusEnum.END_STATUS)) throw new LgException(GlobalCode.ALREADY_END_APPLY); // 종료된 지원서

        UserTb userTb =lawFirmApplyTb.getUserTb();
        lawFirmApplyRepository.confirm(com.kr.lg.common.enums.entity.element.AcceptEnum.NON_ACCEPT, ApplyStatusEnum.END_STATUS, new Timestamp(System.currentTimeMillis()), lawFirmApplyTb.getLawFirmAppyId());

        if (userTb.getLawFirmId() != null) { // 로펌을 이미 가입한 경우
            return false;
        }else if (AcceptEnum.ACCEPT.equals(LawFLayer.getAccept())) { // 로펌 가입 승인
            userRepository.updateLawFirm(userTb.getUserId(), lawFirmApplyTb.getLawFirmTb(), new Timestamp(System.currentTimeMillis()));
            lawFirmApplyRepository.accept(com.kr.lg.common.enums.entity.element.AcceptEnum.ACCEPT, lawFirmApplyTb.getLawFirmAppyId());
        } else if (AcceptEnum.NON_ACCEPT.equals(LawFLayer.getAccept())){ // 로펌 가입 거절
            lawFirmApplyRepository.refuse(com.kr.lg.common.enums.entity.element.AcceptEnum.NON_ACCEPT, lawFirmApplyTb.getLawFirmAppyId());
        }

        return true;

    }

    @Override
    public void enrollLawFirm(LawFLayer LawFLayer) throws LgException {
        UserTb userTb = LawFLayer.getUserTb();
        lawFirmUtils.checkPermission(userTb); // 생성 권한 확인
        if (lawFirmRepository.countByName(LawFLayer.getName()) > 0) throw new LgException(GlobalCode.OVER_LAP_LAW_FIRM_NAME); // 중복 로펌명
        String profile = lawFirmUtils.getImageUrl(LawFLayer.getProfile()); // 프로필 이미지
        String background = lawFirmUtils.getImageUrl(LawFLayer.getBackground()); // 배경 이미지
        LawFirmTb lawFirmTb = lawFirmRepository.save(LawFirmTb.builder()
                .userTb(userTb)
                .name(LawFLayer.getName())
                .introduction(LawFLayer.getIntroduction())
                .profile(profile)
                .background(background)
                .build());
        userRepository.updateLawFirm(userTb.getUserId(), lawFirmTb, new Timestamp(System.currentTimeMillis()));
    }
}
