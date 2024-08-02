package com.kr.lg.module.login.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.kr.lg.module.login.model.google.GoogleLoginRequestDto;
import com.kr.lg.module.login.model.naver.NaverLoginDto;
import com.kr.lg.module.login.model.naver.NaverLoginRequestDto;
import com.kr.lg.module.login.model.naver.NaverLoginResponseDto;
import com.kr.lg.module.login.model.naver.NaverProp;
import com.kr.lg.module.login.service.NaverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class NaverServiceImpl implements NaverService {

    private final NaverProp naverProp;
    @Override
    public NaverLoginDto naverOAuth(NaverLoginRequestDto requestDto) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", requestDto.getClientId());
        params.add("client_secret", requestDto.getClientSecret());
        params.add("grant_type", requestDto.getGrantType());
        params.add("state", requestDto.getState());
        params.add("code", requestDto.getCode());

        ResponseEntity<String> apiResponseJson = restTemplate.postForEntity(naverProp.getNaverLoginUrl() + "/oauth2.0/token", params, String.class);

        // ObjectMapper를 통해 String to Object로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // NULL이 아닌 값만 응답받기(NULL인 경우는 생략)
        NaverLoginResponseDto naverLoginResponseDto = objectMapper.readValue(apiResponseJson.getBody(), new TypeReference<NaverLoginResponseDto>() {});

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(naverLoginResponseDto.getAccessToken());
        HttpEntity<GoogleLoginRequestDto> httpRequestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(naverProp.getNaverAuthUrl() + "/v1/nid/me", httpRequestEntity, String.class);
        Map<String, Object> jsonResponse = objectMapper.readValue(response.getBody(), Map.class);

        if (jsonResponse.containsKey("resultcode") && jsonResponse.get("resultcode").equals("00")) {
            NaverLoginDto userInfoDto = objectMapper.convertValue(jsonResponse.get("response"), new TypeReference<NaverLoginDto>() {});
            return userInfoDto;
        } else {
            throw new Exception("Naver OAuth failed!");
        }
    }

}
