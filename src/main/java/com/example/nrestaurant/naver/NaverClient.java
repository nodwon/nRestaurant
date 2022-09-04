package com.example.nrestaurant.naver;

import com.example.nrestaurant.naver.dto.SearchLocalReq;
import com.example.nrestaurant.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverClient {

    @Value(value = "${naver.client.id}")
    private String naverClientId;

    @Value(value = "${naver.client.secret}")
    private String naverSecret;

    @Value(value = "${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value(value = "${naver.url.search.image}")
    private String naverImageSearchUrl;

    public SearchLocalRes SearchLocal(SearchLocalReq searchLocalReq){
        var url = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMutiValueMap())
                .build()
                .encode()
                .toUri();

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id",naverClientId);
        headers.set("X-Naver-Client-Secret",naverSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responeType = new ParameterizedTypeReference<SearchLocalRes>(){};

        var responseEntity = new RestTemplate().exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                responeType

        );
        return responseEntity.getBody();
    }
    public void SearchImage(){

    }
}
