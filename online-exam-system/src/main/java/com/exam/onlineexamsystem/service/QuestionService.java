package com.exam.onlineexamsystem.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.exam.onlineexamsystem.model.exam.Question;
import com.exam.onlineexamsystem.model.exam.Quiz;

public interface QuestionService {

	public Question addQuestion(Question question); 
	
	public Question updateQuestion(Question question);
	
	public Set<Question> getQuestions();
	
	public Question getQuestion(Long questionId);
	
	public Set<Question> getQuestionofQuiz(Long quizId);

	void deleteQuestion(Long questionId);

	public void deleteQuestions(List<Long> questionIds);

	public Page<Question> getPaginatedQuestions(int page, int i);

	Set<Question> getQuestionofQuiz(Quiz quiz);

	

	
} 
