package com.hyeonson.yajalal.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Service
public class KakaoService {
    private final static Logger log = LoggerFactory.getLogger(KakaoService.class);
    private static String requestUrl = "https://kapi.kakao.com/v2/user/me";
    private HttpComponentsClientHttpRequestFactory factory;

    public KakaoService(){
        factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(3000);
    }

    public String verifyAccessToken(String accessToken) throws RestClientException{

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate(factory);

        ResponseEntity<KakaoAuthResponse> response = restTemplate.exchange(requestUrl, HttpMethod.POST, entity, KakaoAuthResponse.class);
        return response.getBody().getId();

    }

    public static class KakaoAuthResponse{
        private String id;
        private String connected_at;
        public String getId() {
            return id;
        }
        public String getConnected_at() {
            return connected_at;
        }
    }
}
