package com.exam.onlineexamsystem.service.impliment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.exam.onlineexamsystem.model.exam.Category;
import com.exam.onlineexamsystem.model.exam.Quiz;
import com.exam.onlineexamsystem.repository.QuizRepository;
import com.exam.onlineexamsystem.service.QuizService;


@Service
public class QuizServiceImple implements QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepository.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizes() {
		// TODO Auto-generated method stub
		return new HashSet<>(this.quizRepository.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		// TODO Auto-generated method stub
		return this.quizRepository.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long quizId) {
	    this.quizRepository.deleteById(quizId);
	}

	@Override
	public void deleteQuizzes(List<Long> quizIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Quiz> getPaginatedQuizzes(int page, int i) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Quiz> getQuizzesOfCategory(Category category) {
	    return new HashSet<>(quizRepository.findByCategory(category));
	}



	
	
}
