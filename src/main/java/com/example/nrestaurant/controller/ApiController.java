package com.example.nrestaurant.controller;

import com.example.nrestaurant.wishlist.dto.WishListDto;
import com.example.nrestaurant.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/nrestaurant")
@RequiredArgsConstructor
public class ApiController {

    private  final WishListService wishListService;

    @GetMapping("/search")
    public WishListDto search(@RequestParam String query){
        return wishListService.search(query);
    }
    @PostMapping("")
    public WishListDto add(@RequestBody WishListDto wishListDto){
        log.info("{}", wishListDto);

        return wishListService.add(wishListDto);

    }
    @GetMapping("/all")
    public List<WishListDto> findAll(){
        return wishListService.findAll();
    }
    @DeleteMapping
    public void delete(@PathVariable int index){
        wishListService.delete(index);
    }
    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index){
        wishListService.addVisit(index);
    }
}
