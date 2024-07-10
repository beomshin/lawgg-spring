package com.kr.lg.model.common.global;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class GlobalFile {

    private String path;
    private String oriName;
    private String newName;
    private Long size;

    public GlobalFile(MultipartFile file, String path) {
        this.path = path;
        this.size = file.getSize();
        this.oriName = file.getOriginalFilename();
        String[] arr = path.split("/");
        this.newName = arr[arr.length - 1];
    }
}
