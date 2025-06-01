package com.exam.onlineexamsystem.controller;

import com.exam.onlineexamsystem.model.exam.Quiz;
import com.exam.onlineexamsystem.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/quizzes")
@CrossOrigin("*")  // Allows cross-origin requests (for frontend integration)
public class QuizController {

    @Autowired
    private QuizService quizService;

    // 1️⃣ Add a new quiz
    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        Quiz newQuiz = quizService.addQuiz(quiz);
        return ResponseEntity.ok(newQuiz);
    }

    // 2️⃣ Update an existing quiz
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        Quiz updatedQuiz = quizService.updateQuiz(quiz);
        return ResponseEntity.ok(updatedQuiz);
    }

    // 3️⃣ Get a single quiz by ID
    @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long quizId) {
        Quiz quiz = quizService.getQuiz(quizId);
        return quiz != null ? ResponseEntity.ok(quiz) : ResponseEntity.notFound().build();
    }

    // 4️⃣ Get all quizzes
    @GetMapping("/")
    public ResponseEntity<Set<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getQuizes());
    }

    // 5️⃣ Delete a quiz
    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.noContent().build();
    }
    

    
}
