
package com.kr.lg.module.lawfirm.model.dto;


import com.kr.lg.db.entities.LawFirmTb;
import com.kr.lg.db.entities.UserTb;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LawFirmEnrollDto {

    private LawFirmTb lawFirmTb;
    private UserTb userTb;
    private String title;
    private String introduction;

}
