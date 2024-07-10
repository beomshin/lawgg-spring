package com.kr.lg.web.common.global;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum GlobalCode {

    SUCCESS("00000", "성공"),
    SYSTEM_ERROR("17000", "시스템 에러"),
    FAIL_GENERATE_TOKEN("17001", "토큰생성 실패"),
    NOT_EXIST_USER("17002", "존재하지 않는 사용자입니다."),
    FAIL_LOGIN_INFO("17003", "아이디 또는 비밀번호가 틀립니다."),
    LOCK_USER("17004", "정지된 계정입니다."),
    NOT_USE_USER("17005", "삭제된 계정입니다."),
    EXPIRED_USER("17006", "만료된 계정입니다."),
    PASSWORD_EXPIRED_USER("17007", "비밀번호가 만료되었습니다."),
    EXPIRED_JWT_TOKEN("17008", "토큰만료"),
    PARAMETER_ERROR("17009", "잘못된 파라미터입니다."),
    FAIL_CERTIFICATION("17010", "본인 인증을 실패하였습니다."),
    GC10001("10001", "이미 존재하는 아이디 입니다"),
    FAIL_VERIFY_EMAIL("10002", "이메일 인증에 실패했습니다."),
    ALREADY_VERIFY_EMAIL("10003", "이미 인증 완료했습니다."),
    NOT_EXIST_ENROLL_ID("10004", "회원가입 아이디가 존재하지 않습니다."),
    FAIL_UPDATE_PASSWORD("10005", "패스워드 업데이트를 실패했습니다."),
    MATCH_PASSWORD("10006", "기존에 비밀번호와 동일합니다."),
    UN_MATCH_PASSWORD("10007", "비밀번호가 일치하지 않습니다."),
    NOT_EXIST_BOARD("10009", "존재하지 않는 게시판 입니다."),
    REPORT_BOARD("10010", "신고 접수된 게시판입니다."),
    UN_MATCH_BOARD_USER("10011", "게시판 생성 유저와 일치하지 않습니다."),
    FAIL_DELETE_BOARD("10012", "게시판 삭제를 실패했습니다."),
    FAIL_UPDATE_BOARD("10013", "게시판 수정을 실패했습니다."),
    FAIL_REPORT_BOARD("10015", "게시판 신고에 실패했습니다."),
    ALREADY_RECOMMEND_BOARD("10016", "이미 게시판을 추천했습니다."),
    ALREADY_REPORT_BOARD("10017", "이미 게시판을 신고했습니다."),
    UN_MATCHED_LAW_FIRM_USER("10018", "해당 로펌 유저가 아닙니다."),
    NOT_EXIST_LAW_FIRM("10019", "로펌이 존재하지 않습니다."),
    ALREADY_ENROLL_LAW_FIRM("10019", "이미 로펌에 가입하셨습니다."),
    ALREADY_APPLY_LAW_FIRM("10020", "이미 로펌에 신청하였습니다."),
    NOT_EXIST_MEMBER("10021", "존재하지 않는 아이디입니다.."),
    NOT_SEND_SELF("10022", "자신에게 메일을 보낼수 없습니다."),
    NOT_EXIST_MESSAGE("10023", "메일이 존재하지 않습니다."),
    DELETE_MESSAGE("10024", "삭제된 메세지입니다."),
    FAIL_ACCESS("10025", "잘못된 접근입니다."),
    FAIL_FILE_UPLOAD("10026", "파일 업로드에 실패했습니다."),
    DELETE_BOARD("10027", "삭제된 게시판 입니다."),
    NOT_EXIST_BOARD_COMMENT("10028", "존재하지 않는 게시판 댓글 입니다."),
    FAIL_DELETE_RECOMMEND_BOARD("10029", "게시판 추천 취소에 실패 했습니다."),
    FAIL_LAW_FIRM_DELETE("10030", "로펌 탈퇴에 실패했습니다."),
    FAIL_DELETE_COMMENT("10031", "게시판 댓글 삭제에 실패했습니다.."),
    FAIL_REPORT_BOARD_COMMENT("10032", "게시판 댓글 신고에 실패했습니다."),
    FAIL_UPDATE_BOARD_COMMENT("10033", "게시판 댓글 수정을 실패했습니다."),
    NOT_EXIST_APPLY_INFO("10034", "로펌 신청 정보가 없습니다."),
    UNDER_TIER_ENROLL_LAW_FIRM("10035", "로펌 생성에 필요한 티어가 낮습니다."),
    NON_AUTH_USER("10036", "본인 인증 미인증 유저 입니다."),
    OVER_LAP_LAW_FIRM_NAME("10037", "중복 된 로펌명 입니다.."),
    DELETE_LAW_FIRM("10038", "삭제된 로펌 입니다."),
    FAIL_ENROLL_LAW_FIRM("10039", "트라이얼 등록에 실패했습니다."),
    NOT_EXIST_TRIAL("10040", "존재하지 않는 게시판 입니다."),
    ALREADY_DELETE_BOARD("10044", "이미 삭제된 게시판 입니다."),
    FAIL_ENROLL_BOARD("10045", "게시판 등록에 실패했습니다."),
    ALREADY_DELETE_BOARD_COMMENT("10046", "이미 삭제된 게시판 댓글 입니다."),
    ALREADY_END_APPLY("10047", "종료된 로펌 지원입니다."),
    UN_MATCHED_RECEIVER("10048", "메일 작성자가 수신한 메일이 아닙니다."),
    ALREADY_RECOMMEND_TRIAL("10049", "이미 트라이얼을 추천했습니다."),
    ALREADY_DELETE_TRIAL("10050", "이미 트라이얼을 삭제했습니다."),
    DELETE_TRIAL("10051", "삭제된 트라이얼 입니다."),
    REPORT_TRIAL("10052", "신고 접수된 트라이얼입니다."),
    ALREADY_DELETE_TRIAL_COMMENT("10053", "이미 삭제된 트라이얼 댓글 입니다."),
    FAIL_CANCEL_APPLY("10054", "로펌 취소하기를 실패하였습니다."),
    OVERLAP_NICK_NAME("10055", "닉네임이 중복됩니다."),
    FAIL_UPDATE_LIVE("10056", "재판 시작을 실패했습니다."),
    NOT_EXIST_ALERT("10057", "존재하지 않는 알림입니다."),
    NOT_EXIST_FILE("10058", "파일이 존재하지 않습니다."),
    FAIL_FILE_CONDITION("10059", "파일 업로드 조건에 부합하지 않습니다."),
    FAIL_PORTONE_GET_INFO("10060", "아임포트 인증 정보 조회에 실패했습니다."),
    ;

    private final String code;
    private final String msg;

    public static boolean isSuccess(GlobalCode code) {
        return SUCCESS.equals(code);
    }

}
