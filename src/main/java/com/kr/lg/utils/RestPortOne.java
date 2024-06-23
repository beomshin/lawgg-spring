package com.kr.lg.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.kr.lg.exception.LgException;
import com.kr.lg.web.common.global.GlobalCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

@Slf4j
@Component
public class RestPortOne {

    @Value("${portone.rest.key}")
    private String portoneKey;

    @Value("${portone.rest.secret}")
    private String portoneSecret;

    @Value("${portone.url.token}")
    private String portoneTokenUrl;

    @Value("${portone.url.certificate}")
    private String portoneCertUrl;

    public HashMap getPersonalInfo(String imp_uid) throws LgException {
       try {

           RestTemplate restTemplate = new RestTemplate();

           ObjectMapper objectMapper = new ObjectMapper();
           objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
           objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // NULL이 아닌 값만 응답받기(NULL인 경우는 생략)

           HttpHeaders headers = new HttpHeaders();
           headers.setContentType(MediaType.APPLICATION_JSON);

           HashMap<String, String> hashMap = new HashMap<>();
           hashMap.put("imp_key", portoneKey);
           hashMap.put("imp_secret", portoneSecret);

           ResponseEntity<String> tokenApiRes = restTemplate.postForEntity(portoneTokenUrl + "users/getToken", new HttpEntity<>(hashMap, headers), String.class);

           HashMap<String, Object> tokenBody = objectMapper.readValue(tokenApiRes.getBody(), new TypeReference<HashMap>() {});
           HashMap<String, Object> tokenBody2 = objectMapper.convertValue(tokenBody.get("response"), HashMap.class);

           headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
           headers.set("Authorization", (String) tokenBody2.get("access_token"));

           ResponseEntity<String> respEntity = restTemplate.exchange(portoneCertUrl + imp_uid, HttpMethod.GET,  new HttpEntity<String>("", headers), String.class);

           HashMap<String, Object> certApiRes = objectMapper.readValue(respEntity.getBody(), new TypeReference<HashMap>() {});
           HashMap<String, Object> certApiRes2 = objectMapper.convertValue(certApiRes.get("response"), HashMap.class);

           log.debug("아임포트 유저 데이터 응답 데이터 : [{}]", certApiRes);

           return certApiRes2;
       } catch (Exception e) {
           log.error("{}", e.getMessage());
           throw new LgException(GlobalCode.FAIL_PORTONE_GET_INFO);
       }
    }


}
