package com.kr.lg.module.login.service.impl;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {


    @Value("${lg.redirect.url.home}")
    String lgRedirectHomeUrl;

    @Override
    @Transactional
    public String redirect(UserTb userTb) {
        String refreshToken = null;
        Map<String, Object> params = new HashMap<>();
        params.put("token", refreshToken);

        String paramStr = params.entrySet().stream()
                .map(param -> param.getKey() + "=" + param.getValue())
                .collect(Collectors.joining("&"));

        return lgRedirectHomeUrl
                + "?"
                + paramStr;
    }

}
