package com.kr.lg.common.utils;

import com.kr.lg.db.entities.TierTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.web.common.global.GlobalFile;
import com.kr.lg.service.file.FileService;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.enums.AuthEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class LawFirmUtils {

    private FileService<GlobalFile> fileService;

    public void isLawFirmManager(UserTb userTb1, UserTb userTb2) throws LgException {
        if (userTb1.getUserId() != userTb2.getUserId()) throw new LgException(GlobalCode.FAIL_ACCESS); // 로펌 길드장만 허용 할수있음
    }

    public void checkPermission(UserTb userTb) throws LgException {
        TierTb tierTb = userTb.getTierId();
        if (tierTb == null || Long.parseLong(tierTb.getScore()) < 2000) {
            throw new LgException(GlobalCode.UNDER_TIER_ENROLL_LAW_FIRM); // 티어 부족
        } else if (userTb.getLawFirmId() != null) {
            throw new LgException(GlobalCode.ALREADY_ENROLL_LAW_FIRM); // 이미 로펌에 가입된 경우
        } else if (!AuthEnum.AUTH_STATUS.equals(userTb.getAuthFlag())) {
            throw new LgException(GlobalCode.NON_AUTH_USER); // 본인 인증 미인증 유저
        }
    }

    public String getImageUrl(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        return fileService.uploadSingle(file).getPath();
    }

    public void isRightDeleteUser(UserTb userTb, Long lawfirmId) throws LgException {
        if (!(userTb.getLawFirmId() != null) || userTb.getLawFirmId().getLawFirmId() != lawfirmId) {
            throw new LgException(GlobalCode.FAIL_LAW_FIRM_DELETE); // 잘못된 로펌 삭제 접근
        }
    }
}
