package com.exam.onlineexamsystem.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.onlineexamsystem.model.exam.Question;
import com.exam.onlineexamsystem.model.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	Set<Question> findByQuiz(Quiz quiz);



}
