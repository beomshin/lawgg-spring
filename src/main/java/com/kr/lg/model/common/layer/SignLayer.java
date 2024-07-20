package com.kr.lg.model.common.layer;


import com.kr.lg.common.enums.entity.type.SnsType;
import com.kr.lg.model.net.request.sign.SendERequest;
import com.kr.lg.model.net.request.sign.SignURequest;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class SignLayer {

    private Long userId;
    private String loginId;
    private String password;
    private String nickName;
    private String email;
    private Integer personalPeriod;
    private String txId;
    private String code;
    private String name;
    private SnsType snsType;


    public SignLayer(SignURequest request) {
        this.loginId = request.getLoginId();
        this.password = request.getPassword();
        this.nickName = request.getNickName();
        this.personalPeriod = request.getPersonalPeriod();
        this.snsType = SnsType.LG_SNS_TYPE;
    }

    public SignLayer(SendERequest requestDto) {
        this.email = requestDto.getEmail();
    }

    public SignLayer(String txId, String code) {
        this.txId = txId;
        this.code = code;
    }

    public SignLayer(String loginId) {
        this.loginId = loginId;
    }


}
