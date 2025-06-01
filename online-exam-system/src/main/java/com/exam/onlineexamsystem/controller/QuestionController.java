package com.exam.onlineexamsystem.controller;

import com.exam.onlineexamsystem.model.exam.Question;
import com.exam.onlineexamsystem.model.exam.Quiz;
import com.exam.onlineexamsystem.service.QuestionService;
import com.exam.onlineexamsystem.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/questions")
@CrossOrigin("*") // Allows cross-origin requests (for frontend integration)
public class QuestionController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    // 1️⃣ Add a new question
    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question newQuestion = questionService.addQuestion(question);
        return ResponseEntity.ok(newQuestion);
    }

    // 2️⃣ Update an existing question
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        Question updatedQuestion = questionService.updateQuestion(question); // Fixed typo
        return ResponseEntity.ok(updatedQuestion);
    }

    // 3️⃣ Get a single question by ID
    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long questionId) {
        Question question = questionService.getQuestion(questionId);
        return question != null ? ResponseEntity.ok(question) : ResponseEntity.notFound().build();
    }

    // 4️⃣ Get all questions
    @GetMapping("/")
    public ResponseEntity<Set<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getQuestions());
    }

    // 5️⃣ Get questions by quiz ID with validation and shuffling
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsByQuiz(@PathVariable Long quizId) {
        Quiz quiz = quizService.getQuiz(quizId);

        if (quiz == null) {
            return ResponseEntity.notFound().build();
        }

        Set<Question> questions = quiz.getQuestion();
        List<Question> questionList = new ArrayList<>(questions);

        // Ensure `numberOfQestion` is parsed safely
        int numberOfQuestions = 0;
        try {
            numberOfQuestions = Integer.parseInt(quiz.getNumberOfQestion());
        } catch (NumberFormatException e) {
            numberOfQuestions = questionList.size(); // If invalid, use all questions
        }

        // Limit the number of questions based on quiz settings
        if (questionList.size() > numberOfQuestions) {
            questionList = questionList.subList(0, numberOfQuestions);
        }

        // Shuffle questions for randomness
        Collections.shuffle(questionList);

        return ResponseEntity.ok(questionList);
    }

    // 6️⃣ Delete a question
    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }
}
