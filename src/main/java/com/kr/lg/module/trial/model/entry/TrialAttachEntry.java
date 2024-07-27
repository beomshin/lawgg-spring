package com.kr.lg.module.trial.model.entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.db.entities.BoardAttachTb;
import com.kr.lg.db.entities.TrialAttachTb;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // NULL 제외 속성
public class TrialAttachEntry {

    private long trialAttachId; // boardAttach 식별자
    private String path; // URL
    private String oriName; // 원본 파일명
    private String newName; // 변경 파일명
    private long size; // 파일 사이즈

    public TrialAttachEntry(TrialAttachTb trialAttachTb) {
        this.trialAttachId = trialAttachTb.getTrialAttachId();
        this.path = trialAttachTb.getPath();
        this.oriName = trialAttachTb.getOriName();
        this.newName = trialAttachTb.getNewName();
        this.size = trialAttachTb.getSize();
    }
}
