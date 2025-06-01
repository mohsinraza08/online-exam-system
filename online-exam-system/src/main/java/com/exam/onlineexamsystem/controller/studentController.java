package com.exam.onlineexamsystem.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.exam.onlineexamsystem.dto.QuestionResult;

import com.exam.onlineexamsystem.model.studentModel;
import com.exam.onlineexamsystem.model.exam.Category;
import com.exam.onlineexamsystem.model.exam.Question;
import com.exam.onlineexamsystem.model.exam.Quiz;
import com.exam.onlineexamsystem.service.CategoryService;
import com.exam.onlineexamsystem.service.QuestionService;
import com.exam.onlineexamsystem.service.QuizService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/studenthome")
public class studentController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@GetMapping("")
	public String teacherHome(HttpSession session, HttpServletResponse response, Model model) {
		// Prevent caching for security
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		// Get user role from session
		String userRole = Optional.ofNullable((String) session.getAttribute("userRole")).orElse("").toLowerCase();

		// If the user is not a teacher, redirect to login
		if (!"student".equals(userRole)) {
			return "redirect:/login";
		}

		// ✅ Add category list to the model
		model.addAttribute("categories", categoryService.getCategories());
		return "student/studenthome"; // Ensure "teacherhome.html" exists in templates/teacher/
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
		try {
			session.invalidate();
			redirectAttributes.addFlashAttribute("message", "You have successfully logged out.");
			return "redirect:/login";
		} catch (Exception ex) {
			return "redirect:/login";
		}
	}

	// Show quizzes under selected category
	@GetMapping("/category/{categoryId}")
	public String getQuizzesByCategory(@PathVariable Long categoryId, Model model) {
		Category category = categoryService.getCategory(categoryId);
		Set<Quiz> quizzes = quizService.getQuizzesOfCategory(category);

		model.addAttribute("categoryTitle", category.getTitle());
		model.addAttribute("quizzes", quizzes);
		return "student/quiz_list";
	}

	// Show start quiz page
	@GetMapping("/start/{quizId}")
	public String startQuiz(@PathVariable Long quizId, Model model, HttpSession session, HttpServletResponse response) {
		Quiz quiz = quizService.getQuiz(quizId);
		model.addAttribute("quiz", quiz);
		return "student/quiz_start";
	}

	@GetMapping("/quiz/attempt/{quizId}")
	public String attemptQuiz(@PathVariable Long quizId, Model model, HttpSession session,
			HttpServletResponse response) {

		Quiz quiz = quizService.getQuiz(quizId);
		int numberOfQuestions = Integer.parseInt(quiz.getNumberOfQestion());
		Set<Question> questions = questionService.getQuestionofQuiz(quiz);

		List<Question> questionList = new ArrayList<>(questions);
		Collections.shuffle(questionList);

		int numberofQuestions = Integer.parseInt(quiz.getNumberOfQestion());
		if (questionList.size() > numberOfQuestions) {
			questionList = questionList.subList(0, numberOfQuestions);
		}

		// ✅ Retrieve student info from session and add to model
		studentModel student = (studentModel) session.getAttribute("loggedInStudent");
		if (student != null) {
			model.addAttribute("student", student);
		}
		model.addAttribute("quiz", quiz);

		model.addAttribute("questions", questionList);
		return "student/quiz_now";
	}

	@PostMapping("/quiz/submit")
	public String submitQuiz(@RequestParam("quizId") Long quizId, @RequestParam MultiValueMap<String, String> answer,
	                         Model model, HttpSession session) {

	    Quiz quiz = quizService.getQuiz(quizId);
	    Set<Question> allQuestions = questionService.getQuestionofQuiz(quiz);

	    int totalQuestions = allQuestions.size();
	    int attempted = 0;
	    int correct = 0;

	    List<QuestionResult> questionResults = new ArrayList<>();

	    for (Question question : allQuestions) {
	        String quesKey = "answer[" + question.getQuesid() + "]";
	        String givenAnswer = answer.getFirst(quesKey);
	        boolean isCorrect = false;

	        if (givenAnswer != null) {
	            attempted++;
	            if (givenAnswer.trim().equalsIgnoreCase(question.getAnswer().trim())) {
	                correct++;
	                isCorrect = true;
	            }
	        }

	        questionResults.add(new QuestionResult(question, givenAnswer, isCorrect));
	    }

	    double score = correct * (100.0 / totalQuestions);

	    model.addAttribute("quiz", quiz);
	    model.addAttribute("totalQuestions", totalQuestions);
	    model.addAttribute("attempted", attempted);
	    model.addAttribute("correct", correct);
	    model.addAttribute("score", score);
	    model.addAttribute("questionResults", questionResults); // <-- Add to model

	    return "student/quiz_result";
	}

}
