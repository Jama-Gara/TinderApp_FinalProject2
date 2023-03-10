package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    @Query("SELECT f FROM Favorites f WHERE f.likedBy.id = :likedById AND f.likedUser.id = :likedUserId")
    List<Favorites> findByLikedByAndLikedUser(@Param("likedById") Long likedById, @Param("likedUserId") Long likedUserId);
    @Query("SELECT f FROM Favorites f WHERE f.status = :status AND f.likedBy.id = :likedById")
    List<Favorites> findByStatusAndLikedBy(@Param("status") String status, @Param("likedById") Long likedById);
    @Query("SELECT Distinct f FROM Favorites f WHERE f.likedUser.id = :likedUserId AND f.likedBy.id = :likedById")
    Favorites findByLikedUserAndLikedBy(@Param("likedUserId") Long likedUserId, @Param("likedById") Long likedById);
    @Override
    void delete(Favorites entity);
}


