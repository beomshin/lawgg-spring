package com.kr.lg.module.thirdparty.service.impl;

import com.kr.lg.module.thirdparty.exception.ThirdPartyException;
import com.kr.lg.module.thirdparty.exception.ThirdPartyResultCode;
import com.kr.lg.module.thirdparty.service.FileService;
import com.kr.lg.common.utils.AwsS3Utils;
import com.kr.lg.model.global.FileDto;
import com.kr.lg.common.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileServiceImpl implements FileService<FileDto> {

    @Value("${file.size.image}")
    private int IMAGE_MAX_SIZE;
    @Value("${file.size.video}")
    private int VIDEO_MAX_SIZE;
    @Value("${file.size.replay}")
    private int REPLAY_MAX_SIZE;
    private final Set<String> accessFileExt, accessVideoExt, accessReplayExt;
    private final AwsS3Utils awsS3Utils;


    public FileServiceImpl(AwsS3Utils awsS3Utils) {
        this.awsS3Utils = awsS3Utils;
        this.accessFileExt = new HashSet<>(Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp"));
        this.accessVideoExt = new HashSet<>(Arrays.asList(".mp4", ".avi", ".wmv", ".mpg", ".mkv", ".webm"));
        this.accessReplayExt = new HashSet<>(Collections.singletonList(".rofl"));
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        return files.stream().map(file -> this.upload(file,IMAGE_MAX_SIZE, accessFileExt)).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public FileDto uploadSingle(MultipartFile file) {
        return this.upload(file, IMAGE_MAX_SIZE, accessFileExt);
    }

    @Override
    public FileDto uploadVideo(MultipartFile file) {
        return this.upload(file, VIDEO_MAX_SIZE, accessVideoExt);
    }

    @Override
    public FileDto uploadReplay(MultipartFile file) {
        return this.upload(file, REPLAY_MAX_SIZE, accessReplayExt);
    }

    private FileDto upload(MultipartFile file, Integer maxSize, Set<String> accessSet) {
        try {
            if (file == null || file.isEmpty()) throw new ThirdPartyException(ThirdPartyResultCode.NOT_EXIST_FILE);
            String ext = FileUtils.getAccessFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
            log.debug("[파일 업로드] 파일명 [{}], 사이즈 [{}], 최대 사이즈 [{}], 확장자 [{}]", file.getOriginalFilename(), file.getSize(), maxSize, ext);
            if (file.getSize() >= maxSize || !accessSet.contains(ext)) throw new ThirdPartyException(ThirdPartyResultCode.FAIL_FILE_CONDITION);
            String path = awsS3Utils.fileUploadToS3(file, ext);
            return path != null ? new FileDto(file, path) : null;
        } catch (ThirdPartyException e) {
            log.error("{}", e.getMessage());
            return null;
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }
}
