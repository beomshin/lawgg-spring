package com.kr.lg.model.annotation;

import com.kr.lg.db.entities.LawFirmTb;
import com.kr.lg.db.entities.TierTb;
import com.kr.lg.db.entities.UserTb;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class UserAdapter extends User {

    private final UserTb userTb;
    private final LawFirmTb lawFirmTb;
    private final TierTb tierTb;

    public UserAdapter(UserTb userTb, Collection<? extends GrantedAuthority>  authorities) {
        super(String.valueOf(userTb.getUserId()), userTb.getPassword(), authorities);
        this.userTb = userTb;
        this.lawFirmTb = userTb.getLawFirmTb();
        this.tierTb = userTb.getTierTb();
    }

}
