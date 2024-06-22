package com.kr.lg.model.web.common.layer;


import com.kr.lg.enums.entity.element.SnsEnum;
import com.kr.lg.model.web.net.request.sign.SendERequest;
import com.kr.lg.model.web.net.request.sign.SignURequest;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

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
    private SnsEnum snsType;


    public SignLayer(SignURequest request) {
        this.loginId = request.getLoginId();
        this.password = request.getPassword();
        this.nickName = request.getNickName();
        this.personalPeriod = request.getPersonalPeriod();
        this.snsType = SnsEnum.LG_SNS_TYPE;
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
