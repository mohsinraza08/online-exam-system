package com.exam.onlineexamsystem.service.impliment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.exam.onlineexamsystem.model.exam.Question;
import com.exam.onlineexamsystem.model.exam.Quiz;
import com.exam.onlineexamsystem.repository.QuestionRepository;
import com.exam.onlineexamsystem.service.QuestionService;


@Service
public class QuestionServiceImple implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public Question addQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestions() {
		// TODO Auto-generated method stub
		return new HashSet(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {
		// TODO Auto-generated method stub
		return this.questionRepository.findById(questionId).get();
	}
	@Override
	public void deleteQuestion(Long questionId) {
	    this.questionRepository.deleteById(questionId);
	}


	@Override
	public Set<Question> getQuestionofQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.questionRepository.findByQuiz( quiz);
	}

	@Override
	public void deleteQuestions(List<Long> questionIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Question> getPaginatedQuestions(int page, int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
