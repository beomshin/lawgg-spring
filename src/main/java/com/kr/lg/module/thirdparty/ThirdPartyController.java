package com.kr.lg.module.thirdparty;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.model.net.request.auth.DanalCRequest;
import com.kr.lg.module.thirdparty.model.res.PublicCertificationsDanalResponse;
import com.kr.lg.module.thirdparty.service.ThirdPartyService;
import com.kr.lg.web.dto.global.GlobalCode;
import com.kr.lg.web.dto.root.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ThirdPartyController {

    private ThirdPartyService thirdPartyService;

    @PostMapping("/api/certifications/danal")
    public ResponseEntity<?> certificationsDanal(@RequestBody DanalCRequest request, @UserPrincipal UserAdapter userAdapter) throws LgException {
        Boolean success = thirdPartyService.certificationsDanal(request, userAdapter.getUserTb());
        DefaultResponse body = success ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_CERTIFICATION);
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/api/public/certifications/danal")
    public ResponseEntity<?> publicCertificationsDanal(@RequestBody DanalCRequest request) throws Exception {
        return ResponseEntity.ok().body(new PublicCertificationsDanalResponse(thirdPartyService.certificationsDanal(request)));
    }

}
