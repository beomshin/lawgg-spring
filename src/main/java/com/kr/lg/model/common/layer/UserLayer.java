package com.kr.lg.model.common.layer;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.user.model.req.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Slf4j
@ToString
public class UserLayer {

    private String ci;
    private String loginId;
    private String password;
    private UserTb userTb;
    private MultipartFile profile;
    private String nickName;
    private Integer page;
    private Integer pageNum;
    private String keyword;
    private String email;
    private String hashEmail;
    private Long id;
    private String error_code;
    private String error_msg;
    private String imp_uid;
    private String merchant_uid;
    private String pg_provider;
    private String pg_type;
    private Boolean success;

    public UserLayer(UserTb userTb) {
        this.userTb = userTb;
    }

    public UserLayer(FindUserIdRequest request) throws NoSuchAlgorithmException {
        this.error_code = request.getError_code();
        this.error_msg = request.getError_msg();
        this.imp_uid = request.getImp_uid();
        this.merchant_uid = request.getMerchant_uid();
        this.pg_provider = request.getPg_provider();
        this.pg_type = request.getPg_type();
        this.success = request.getSuccess();
    }

    public UserLayer(VerifyUserRequest request) throws NoSuchAlgorithmException {
        this.loginId = request.getLoginId();
        this.error_code = request.getError_code();
        this.error_msg = request.getError_msg();
        this.imp_uid = request.getImp_uid();
        this.merchant_uid = request.getMerchant_uid();
        this.pg_provider = request.getPg_provider();
        this.pg_type = request.getPg_type();
        this.success = request.getSuccess();
    }

    public UserLayer(UpdatePURequest request) throws NoSuchAlgorithmException {
        this.loginId = request.getLoginId();
        this.password = request.getPassword();
    }

    public UserLayer(UpdateIURequest request, UserTb userTb) {
        this.userTb = userTb;
        this.nickName = request.getNickName();
        this.password = request.getPassword();
        this.email = request.getEmail();
    }

    public UserLayer(UpdateUPRequest request, UserTb userTb) {
        this.profile = request.getProfile();
        this.userTb = userTb;
    }

    public UserLayer(VerifyPasswordRequest requestDto, UserTb userTb) {
        this.password = requestDto.getPassword();
        this.userTb = userTb;
    }

    public UserLayer(FindUserBoardsRequest requestDto, UserTb userTb) {
        this.userTb = userTb;
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.keyword = requestDto.getKeyword();
    }

    public UserLayer(FindUserAlertRequest requestDto, UserTb userTb) {
        this.userTb = userTb;
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.keyword = requestDto.getKeyword();
    }

    public UserLayer(UpdateUARequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.userTb = userTb;
    }
}
