package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Favorites;
import com.example.registrationlogindemo.repository.FavoritesRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavoritesService {
    private final FavoritesRepository favoritesRepository;
    public FavoritesService(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    public List<Favorites> findByStatusAndLikedBy(String like, Long currentUserId) {
        return favoritesRepository.findByStatusAndLikedBy(like, currentUserId);
    }
    public Favorites findByLikedUserAndLikedBy(Long userId, Long currentUserId) {
        return favoritesRepository.findByLikedUserAndLikedBy(userId, currentUserId);
    }
    public void delete(Favorites favorite) {
        favoritesRepository.delete(favorite);
    }
}
