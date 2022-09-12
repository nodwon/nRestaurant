package com.example.nrestaurant.wishlist.service;

import com.example.nrestaurant.naver.NaverClient;
import com.example.nrestaurant.naver.dto.SearchImageReq;
import com.example.nrestaurant.naver.dto.SearchLocalReq;
import com.example.nrestaurant.wishlist.dto.WishListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;

    public WishListDto search(String query){
        // 지역검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);
        var searchLocalRes = naverClient.searchLocal(searchLocalReq);

        if(searchLocalRes.getTotal()>0){
            var localItem = searchLocalRes.getItems().stream().findFirst().get();

            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>",""); // (갈비)를 괄호 제거
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);
            // 이미지 검색
            var searchImageRes = naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal()>0){
                var imageItem = searchImageRes.getItems().stream().findFirst().get();
                //결과 리턴
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(searchLocalRes.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());

                return result;
            }
        }
        return new WishListDto();

    }

}
