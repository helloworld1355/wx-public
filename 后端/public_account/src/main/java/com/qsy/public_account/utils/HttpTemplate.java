package com.qsy.public_account.utils;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class HttpTemplate {

    public static String httpGet(String url) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
        return result;
    }

    public static String httpPost(String url, String name) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(url, name, String.class).getBody();
    }

}