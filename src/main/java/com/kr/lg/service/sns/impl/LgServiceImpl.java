package com.kr.lg.service.sns.impl;

import com.kr.lg.db.entities.TierTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.common.enums.entity.element.SnsEnum;
import com.kr.lg.db.repositories.NickNameRepository;
import com.kr.lg.db.repositories.TierRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.web.jwt.JwtService;
import com.kr.lg.model.common.sns.lg.LgLoginDto;
import com.kr.lg.service.sns.LgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LgServiceImpl implements LgService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TierRepository tierRepository;
    private final NickNameRepository nickNameRepository;
    private final String BRONZE_3 = "Bronze_3";

    @Value("${lg.redirect.url.home}")
    String lgRedirectHomeUrl;

    @Override
    public String LgLogin(LgLoginDto lgLoginDto) throws Exception {
        Optional<UserTb> userTb = userRepository.findBySnsIdAndSnsType(lgLoginDto.getSnsId(), lgLoginDto.getSnsType());
        Long userId = null;
        if (userTb.isPresent()) {
            userId = userTb.get().getUserId();
        } else {
            TierTb tierTb = tierRepository.findByKey(BRONZE_3);
            lgLoginDto.setNickname(this.uniqueNickName(lgLoginDto.getSnsType())); // 유니크 닉네임 생성
            UserTb userTb1 = userRepository.save(UserTb.builder()
                    .loginId(getLoginId(lgLoginDto.getSnsType()))
                    .snsId(lgLoginDto.getSnsId())
                    .nickName(lgLoginDto.getNickname())
                    .email(lgLoginDto.getEmail())
                    .name(lgLoginDto.getName())
                    .profile(lgLoginDto.getProfile())
                    .snsType(lgLoginDto.getSnsType())
                    .tierId(tierTb)
                    .build());

            userId = userTb1.getUserId();
        }

        String refreshToken = jwtService.generateRefreshToken(String.valueOf(userId), "/", Arrays.asList("ROLE_USER"));

        Map<String, Object> params = new HashMap<>();
        params.put("token", refreshToken);

        String paramStr = params.entrySet().stream()
                .map(param -> param.getKey() + "=" + param.getValue())
                .collect(Collectors.joining("&"));

        return lgRedirectHomeUrl
                + "?"
                + paramStr;
    }


    public String uniqueNickName(SnsEnum snsEnum) {
        switch (snsEnum) {
            case GOOGLE_SNS_TYPE: return "GLawgg" + new Random().nextInt(9000);
            case KAKAO_SNS_TYPE: return "KLawgg" + new Random().nextInt(9000);
            case NAVER_SNS_TYPE: return "NLawgg" + new Random().nextInt(9000);
            default: return "Lawgg" + new Random().nextInt(9000);
        }
    }

    public String getLoginId(SnsEnum snsType) {
        String prefix = "";
        switch (snsType) {
            case GOOGLE_SNS_TYPE: prefix += "G_"; break;
            case NAVER_SNS_TYPE: prefix += "N_"; break;
            case KAKAO_SNS_TYPE: prefix += "K_"; break;
        }

        return prefix + UUID.randomUUID().toString().replace("-", "").substring(0 ,30);
    }
}
