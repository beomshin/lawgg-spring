package com.kr.lg.web.dto.global;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GlobalCode {

    SUCCESS("00000", "성공"),
    SYSTEM_ERROR("17000", "시스템 에러"),
    NOT_EXIST_USER("17002", "존재하지 않는 사용자입니다."),
    PARAMETER_ERROR("17009", "잘못된 파라미터입니다."),
    FAIL_CERTIFICATION("17010", "본인 인증을 실패하였습니다."),
    GC10001("10001", "이미 존재하는 아이디 입니다"),
    FAIL_VERIFY_EMAIL("10002", "이메일 인증에 실패했습니다."),
    ALREADY_VERIFY_EMAIL("10003", "이미 인증 완료했습니다."),
    NOT_EXIST_ENROLL_ID("10004", "회원가입 아이디가 존재하지 않습니다."),
    FAIL_UPDATE_PASSWORD("10005", "패스워드 업데이트를 실패했습니다."),
    MATCH_PASSWORD("10006", "기존에 비밀번호와 동일합니다."),
    UN_MATCH_PASSWORD("10007", "비밀번호가 일치하지 않습니다."),
    FAIL_REPORT_BOARD("10015", "게시판 신고에 실패했습니다."),
    NOT_EXIST_LAW_FIRM("10019", "로펌이 존재하지 않습니다."),
    ALREADY_ENROLL_LAW_FIRM("10019", "이미 로펌에 가입하셨습니다."),
    NOT_EXIST_MEMBER("10021", "존재하지 않는 아이디입니다.."),
    NOT_SEND_SELF("10022", "자신에게 메일을 보낼수 없습니다."),
    NOT_EXIST_MESSAGE("10023", "메일이 존재하지 않습니다."),
    DELETE_MESSAGE("10024", "삭제된 메세지입니다."),
    FAIL_ACCESS("10025", "잘못된 접근입니다."),
    FAIL_FILE_UPLOAD("10026", "파일 업로드에 실패했습니다."),
    FAIL_DELETE_RECOMMEND_BOARD("10029", "게시판 추천 취소에 실패 했습니다."),
    FAIL_LAW_FIRM_DELETE("10030", "로펌 탈퇴에 실패했습니다."),
    FAIL_REPORT_BOARD_COMMENT("10032", "게시판 댓글 신고에 실패했습니다."),
    FAIL_UPDATE_BOARD_COMMENT("10033", "게시판 댓글 수정을 실패했습니다."),
    UNDER_TIER_ENROLL_LAW_FIRM("10035", "로펌 생성에 필요한 티어가 낮습니다."),
    NON_AUTH_USER("10036", "본인 인증 미인증 유저 입니다."),
    OVER_LAP_LAW_FIRM_NAME("10037", "중복 된 로펌명 입니다.."),
    DELETE_LAW_FIRM("10038", "삭제된 로펌 입니다."),
    FAIL_ENROLL_LAW_FIRM("10039", "트라이얼 등록에 실패했습니다."),
    NOT_EXIST_TRIAL("10040", "존재하지 않는 게시판 입니다."),
    UN_MATCHED_RECEIVER("10048", "메일 작성자가 수신한 메일이 아닙니다."),
    ALREADY_RECOMMEND_TRIAL("10049", "이미 트라이얼을 추천했습니다."),
    ALREADY_DELETE_TRIAL("10050", "이미 트라이얼을 삭제했습니다."),
    DELETE_TRIAL("10051", "삭제된 트라이얼 입니다."),
    REPORT_TRIAL("10052", "신고 접수된 트라이얼입니다."),
    ALREADY_DELETE_TRIAL_COMMENT("10053", "이미 삭제된 트라이얼 댓글 입니다."),
    OVERLAP_NICK_NAME("10055", "닉네임이 중복됩니다."),
    FAIL_UPDATE_LIVE("10056", "재판 시작을 실패했습니다."),
    NOT_EXIST_ALERT("10057", "존재하지 않는 알림입니다."),
    NOT_EXIST_FILE("10058", "파일이 존재하지 않습니다."),
    FAIL_FILE_CONDITION("10059", "파일 업로드 조건에 부합하지 않습니다."),
    FAIL_PORTONE_GET_INFO("10060", "아임포트 인증 정보 조회에 실패했습니다."),
    ;

    private final String code;
    private final String msg;

}
