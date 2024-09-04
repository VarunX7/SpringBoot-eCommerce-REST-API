package com.sonata.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sonata.Model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	List<Review> findByUser(Long user);
	List<Review> findByProduct(Long productId);
	
	@Query("SELECT AVG(r.rating) FROM Review r WHERE r.product = :productId")
    Double findAverageRatingByProduct(@Param("productId") Long productId);
}
