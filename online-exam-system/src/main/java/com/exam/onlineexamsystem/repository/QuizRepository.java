package com.exam.onlineexamsystem.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.onlineexamsystem.model.exam.Category;
import com.exam.onlineexamsystem.model.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	Set<Quiz> findByCategory(Category category);



}
