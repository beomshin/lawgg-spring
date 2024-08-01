package com.kr.lg.module.thirdparty;

import com.kr.lg.module.thirdparty.service.FileService;
import com.kr.lg.web.dto.global.FileDto;
import com.kr.lg.web.dto.root.AbstractSpec;
import com.kr.lg.module.thirdparty.model.req.EnrollFileRequest;
import com.kr.lg.module.thirdparty.model.res.EnrollFileResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService<FileDto> fileService;

    @PostMapping("/api/public/v1/enroll/file")
    @ApiOperation(value = "파일 리스트 등록", notes = "파일 리스트 등록을 요청합니다.")
    public ResponseEntity<?> enrollFile(
            @ModelAttribute @Valid EnrollFileRequest request
    )  {
        List<FileDto> files = fileService.uploadMultiple(request.getFiles());
        AbstractSpec spec = EnrollFileResponse.builder()
                .files(files)
                .build();
        return ResponseEntity.ok().body(spec);
    }

}
