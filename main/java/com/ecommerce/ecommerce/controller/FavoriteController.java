package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.response.FavoriteResponse;
import com.ecommerce.ecommerce.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/favorite")

public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }


    @PostMapping("/add/{productId}")
    public FavoriteResponse addFavorite(@RequestParam Long userId, @PathVariable Long productId) {
        return favoriteService.addFavorite(userId, productId);
    }


    @DeleteMapping("/remove/{productId}")
    public void removeFavorite(@RequestParam Long userId, @PathVariable Long productId) {
        favoriteService.removeFavorite(userId, productId);
    }


    @GetMapping("/all")
    public List<FavoriteResponse> getUserFavorites(@RequestParam Long userId) {
        return favoriteService.getUserFavorites(userId);
    }
}
