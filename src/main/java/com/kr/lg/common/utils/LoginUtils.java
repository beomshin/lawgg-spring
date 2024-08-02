package com.kr.lg.common.utils;

import com.kr.lg.common.enums.entity.type.SnsType;

import java.util.Random;
import java.util.UUID;

public class LoginUtils {


    public static String uniqueNickName(SnsType snsType) {
        switch (snsType) {
            case GOOGLE_SNS_TYPE: return "GLawgg" + new Random().nextInt(9000);
            case KAKAO_SNS_TYPE: return "KLawgg" + new Random().nextInt(9000);
            case NAVER_SNS_TYPE: return "NLawgg" + new Random().nextInt(9000);
            default: return "Lawgg" + new Random().nextInt(9000);
        }
    }

    public static String getLoginId(SnsType snsType) {
        String prefix = "";
        switch (snsType) {
            case GOOGLE_SNS_TYPE: prefix += "G_"; break;
            case NAVER_SNS_TYPE: prefix += "N_"; break;
            case KAKAO_SNS_TYPE: prefix += "K_"; break;
        }

        return prefix + UUID.randomUUID().toString().replace("-", "").substring(0 ,30);
    }
}
