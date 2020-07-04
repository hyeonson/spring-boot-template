package com.hyeonson.yajalal;

import com.google.gson.Gson;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class SimpleTest {

    private static final Logger log = LoggerFactory.getLogger(SimpleTest.class);

    @Test
    public void RestTemplateForKakao(){
//        String accessToken = "";
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Authorization", "Bearer " + accessToken);
//
//        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
//
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setReadTimeout(5000);
//        factory.setConnectTimeout(3000);
//        //connection pool 적용
//        HttpClient httpClient = HttpClientBuilder.create()
//                .setMaxConnTotal(100)
//                .setMaxConnPerRoute(5)
//                .build();
//        RestTemplate restTemplate = new RestTemplate(factory);
//
//
//        String url = "https://kapi.kakao.com/v2/user/me";
//
//        try {
//            String responseString = restTemplate.postForObject(url, entity, String.class);
//            Gson gson = new Gson();
//            KakaoAuthResponse response = gson.fromJson(responseString, KakaoAuthResponse.class);
//            log.info("id: {}", response.getId());
//
//        }catch (RestClientException e){
//            log.error(e.toString());
//            return;
//        }

//        String accessToken = "";
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Authorization", "Bearer " + accessToken);
//
//        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(httpHeaders);
//
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setReadTimeout(5000);
//        factory.setConnectTimeout(3000);
//        //connection pool 적용
//        /*HttpClient httpClient = HttpClientBuilder.create()
//                .setMaxConnTotal(100)
//                .setMaxConnPerRoute(5)
//                .build();*/
//        RestTemplate restTemplate = new RestTemplate(factory);
//
//
//        String url = "https://kapi.kakao.com/v2/user/me";
//
//        try {
//            ResponseEntity<KakaoAuthResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, KakaoAuthResponse.class);
//            //String responseString = restTemplate.postForObject(url, entity, String.class);
//            //Gson gson = new Gson();
//            //KakaoAuthResponse response = gson.fromJson(responseString, KakaoAuthResponse.class);
//            log.info("id: {}", response.getBody().getId());
//
//        }catch (RestClientException e){
//            log.error(e.toString());
//            return;
//        }
    }


//    public static class KakaoAuthResponse{
//        private String id;
//        private String connected_at;
//        public String getId() {
//            return id;
//        }
//        public String getConnected_at() {
//            return connected_at;
//        }
//    }
}
