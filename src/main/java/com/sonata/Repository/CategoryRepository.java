package com.sonata.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sonata.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Custom queries can be added here if needed
}
