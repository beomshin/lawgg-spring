package com.kr.lg.module.message.model.dto;

import com.kr.lg.db.entities.UserTb;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageEnrollDto {

    private UserTb receiver;
    private UserTb sender;
    private String title;
    private String content;

}
