package com.exam.onlineexamsystem.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.exam.onlineexamsystem.model.exam.Category;
import com.exam.onlineexamsystem.model.exam.Quiz;

public interface QuizService {
	
	public Quiz addQuiz(Quiz quiz);
	
	public Quiz updateQuiz(Quiz quiz);
	
	public Set<Quiz> getQuizes();
	
    public Quiz getQuiz(Long quizId);
    
    public void deleteQuiz(Long quizId);
   


	public void deleteQuizzes(List<Long> quizIds);

	public Page<Quiz> getPaginatedQuizzes(int page, int i);

	Set<Quiz> getQuizzesOfCategory(Category category);
	
}
