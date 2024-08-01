package com.kr.lg.web.dto.global;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class FileDto {

    private String path;
    private String oriName;
    private String newName;
    private Long size;

    public FileDto(MultipartFile file, String path) {
        this.path = path;
        this.size = file.getSize();
        this.oriName = file.getOriginalFilename();
        String[] arr = path.split("/");
        this.newName = arr[arr.length - 1];
    }
}
