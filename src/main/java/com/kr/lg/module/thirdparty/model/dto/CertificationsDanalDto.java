package com.kr.lg.module.thirdparty.model.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CertificationsDanalDto {

    private String error_code;

    private String error_msg;

    private String imp_uid;

    private String merchant_uid;

    private String pg_provider;

    private String pg_type;

    private Boolean success;

}
