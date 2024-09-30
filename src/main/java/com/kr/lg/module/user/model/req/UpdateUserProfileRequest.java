package com.kr.lg.module.user.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "회원 프로필 업데이트 요청 Body")
public class UpdateUserProfileRequest {

    @Schema(description = "파일")
    @NotNull(message = "프로필 정보가 없습니다.")
    private MultipartFile profile;
}
