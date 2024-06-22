package com.kr.lg.controller.common;

import com.kr.lg.common.service.file.FileService;
import com.kr.lg.model.web.common.global.GlobalFile;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.net.request.common.FileRequest;
import com.kr.lg.model.web.net.response.common.FileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService<GlobalFile> fileService;

    @PostMapping("/api/public/enroll/file")
    public ResponseEntity<DefaultResponse> enrollFile(
            @ModelAttribute FileRequest request
    )  {
        return ResponseEntity.ok().body(new FileResponse(fileService.uploadMultiple(request.getFiles())));
    }

}
