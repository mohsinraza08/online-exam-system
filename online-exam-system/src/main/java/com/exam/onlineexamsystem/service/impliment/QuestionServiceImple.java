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
        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long questionId) {
        return this.questionRepository.findById(questionId).orElse(null);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        this.questionRepository.deleteById(questionId);
    }

    @Override
    public Set<Question> getQuestionofQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);
    }

    // ✅ This is the missing method causing the build error
    @Override
    public Set<Question> getQuestionofQuiz(Long quizId) {
        Quiz quiz = new Quiz();
        quiz.setQid(quizId);
        Set<Question> questions = questionRepository.findByQuiz(quiz);
        return (Set<Question>) List.copyOf(questions); // Or new ArrayList<>(questions);
    }

    @Override
    public void deleteQuestions(List<Long> questionIds) {
        // Implement logic if needed
    }

    @Override
    public Page<Question> getPaginatedQuestions(int page, int size) {
        // Implement logic if needed
        return null;
    }
}
