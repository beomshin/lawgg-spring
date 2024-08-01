package com.kr.lg.module.thirdparty.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService<T> {

    List<T> uploadMultiple(List<MultipartFile> files);
    T uploadSingle(MultipartFile file);
    T uploadVideo(MultipartFile file);
    T uploadReplay(MultipartFile file);
}
