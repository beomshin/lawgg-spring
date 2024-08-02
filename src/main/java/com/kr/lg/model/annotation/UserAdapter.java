package com.kr.lg.model.annotation;

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

    public UserAdapter(UserTb userTb) {
        super(userTb.getLoginId(), userTb.getPassword() == null ? "" : userTb.getPassword(), authorities());
        this.userTb = userTb;
    }

    public UserAdapter(UserTb userTb, Collection<? extends GrantedAuthority>  authorities) {
        super(String.valueOf(userTb.getUserId()), userTb.getPassword(), authorities);
        this.userTb = userTb;
    }

    private static Collection<? extends GrantedAuthority> authorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

}
