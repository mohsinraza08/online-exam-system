package com.exam.onlineexamsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.onlineexamsystem.model.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
