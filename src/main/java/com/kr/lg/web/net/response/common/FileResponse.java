package com.kr.lg.web.net.response.common;

import com.kr.lg.web.common.global.GlobalFile;
import com.kr.lg.web.common.root.DefaultResponse;
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
