package com.kr.lg.model.net.response.common;

import com.kr.lg.model.common.global.GlobalFile;
import com.kr.lg.model.common.root.DefaultResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse extends DefaultResponse { // FileResponse

    private List<GlobalFile> files;

}
