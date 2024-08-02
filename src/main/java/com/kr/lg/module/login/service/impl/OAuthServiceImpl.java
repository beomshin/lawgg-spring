package com.kr.lg.module.login.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.kr.lg.module.login.exception.LoginException;
import com.kr.lg.module.login.exception.LoginResultCode;
import com.kr.lg.module.login.model.dto.GoogleLoginRequestDto;
import com.kr.lg.module.login.model.dto.KakaoLoginRequestDto;
import com.kr.lg.module.login.model.dto.NaverLoginRequestDto;
import com.kr.lg.module.login.model.google.GoogleLoginDto;
import com.kr.lg.module.login.model.google.GoogleLoginResponseDto;
import com.kr.lg.module.login.model.google.GoogleProp;
import com.kr.lg.module.login.model.kakao.KakaoLoginDto;
import com.kr.lg.module.login.model.kakao.KakaoLoginResponseDto;
import com.kr.lg.module.login.model.kakao.KakaoProp;
import com.kr.lg.module.login.model.naver.NaverLoginDto;
import com.kr.lg.module.login.model.naver.NaverLoginResponseDto;
import com.kr.lg.module.login.model.naver.NaverProp;
import com.kr.lg.module.login.service.OAuthService;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {

    private final GoogleProp googleProp;
    private final KakaoProp kakaoProp;
    private final NaverProp naverProp;

    @Override
    public GoogleLoginDto googleOAuth(GoogleLoginRequestDto requestDto) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // Http Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GoogleLoginRequestDto> httpRequestEntity = new HttpEntity<>(requestDto, headers);
        ResponseEntity<String> apiResponseJson = restTemplate.postForEntity(googleProp.getGoogleAuthUrl() + "/token", httpRequestEntity, String.class);

        // ObjectMapper를 통해 String to Object로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // NULL이 아닌 값만 응답받기(NULL인 경우는 생략)
        GoogleLoginResponseDto googleLoginResponse = objectMapper.readValue(apiResponseJson.getBody(), new TypeReference<GoogleLoginResponseDto>() {});

        // 사용자의 정보는 JWT Token으로 저장되어 있고, Id_Token에 값을 저장한다.
        String jwtToken = googleLoginResponse.getIdToken();

        // JWT Token을 전달해 JWT 저장된 사용자 정보 확인
        String requestUrl = UriComponentsBuilder.fromHttpUrl(googleProp.getGoogleAuthUrl() + "/tokeninfo").queryParam("id_token", jwtToken).toUriString();

        String resultJson = restTemplate.getForObject(requestUrl, String.class);

        if(resultJson != null) {
            GoogleLoginDto userInfoDto = objectMapper.readValue(resultJson, new TypeReference<GoogleLoginDto>() {});
            return userInfoDto;
        }
        else {
            throw new LoginException(LoginResultCode.FAIL_GOOGLE_CALLBACK_LOGIN);
        }
    }

    @Override
    public KakaoLoginDto kakaoOAuth(KakaoLoginRequestDto requestDto) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", requestDto.getClientId());
        params.add("client_secret", requestDto.getClientSecret());
        params.add("grant_type", requestDto.getGrantType());
        params.add("redirect_uri", requestDto.getRedirectUri());
        params.add("code", requestDto.getCode());

        ResponseEntity<String> apiResponseJson = restTemplate.postForEntity(kakaoProp.getKakaoLoginUrl() + "/oauth/token", params, String.class);

        // ObjectMapper를 통해 String to Object로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // NULL이 아닌 값만 응답받기(NULL인 경우는 생략)
        KakaoLoginResponseDto kakaoLoginResponseDto = objectMapper.readValue(apiResponseJson.getBody(), new TypeReference<KakaoLoginResponseDto>() {});

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(kakaoLoginResponseDto.getAccessToken());
        HttpEntity<GoogleLoginRequestDto> httpRequestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(kakaoProp.getKakaoAuthUrl() + "/v2/user/me", httpRequestEntity, String.class);
        Map<String, Object> jsonResponse = objectMapper.readValue(response.getBody(), Map.class);
        log.debug("{}", jsonResponse);


        if (jsonResponse != null) {
            KakaoLoginDto userInfoDto = objectMapper.convertValue(jsonResponse, new TypeReference<KakaoLoginDto>() {});
            return userInfoDto;
        } else {
            throw new LoginException(LoginResultCode.FAIL_KAKAO_CALLBACK_LOGIN);
        }
    }

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
            throw new LoginException(LoginResultCode.FAIL_NAVER_CALLBACK_LOGIN);
        }
    }
}
