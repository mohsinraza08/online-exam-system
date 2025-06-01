package com.exam.onlineexamsystem.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.exam.onlineexamsystem.model.exam.Category;
import com.exam.onlineexamsystem.model.exam.Question;
import com.exam.onlineexamsystem.model.exam.Quiz;
import com.exam.onlineexamsystem.service.CategoryService;
import com.exam.onlineexamsystem.service.QuestionService;
import com.exam.onlineexamsystem.service.QuizService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/teacherhome")
public class TeacherController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    // ✅ Teacher Home Page
    @GetMapping("")
    public String teacherHome(HttpSession session, HttpServletResponse response, Model model) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        String userRole = Optional.ofNullable((String) session.getAttribute("userRole")).orElse("").toLowerCase();
        if (!"teacher".equals(userRole)) {
            return "redirect:/login";
        }
        return "teacher/teacherhome";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "You have successfully logged out.");
        return "redirect:/login";
    }

    // ============================================
    // CATEGORY MANAGEMENT
    // ============================================

    @GetMapping("/categories")
    public String viewCategories(Model model) {
        Set<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("activePage", "categories");
        return "teacher/categories";
    }

    @GetMapping("/add-category")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "teacher/add-category";
    }

    @PostMapping("/save-category")
    public String saveCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        categoryService.addCategory(category);
        redirectAttributes.addFlashAttribute("success", "Category added successfully!");
        return "redirect:/teacherhome/categories";
    }

    @GetMapping("/edit-category/{id}")
    public String showEditCategoryForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategory(id);
        if (category == null) {
            return "redirect:/teacherhome/categories";
        }
        model.addAttribute("category", category);
        return "teacher/add-category";
    }

    @PostMapping("/update-category")
    public String updateCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        categoryService.updateCategory(category);
        redirectAttributes.addFlashAttribute("success", "Category updated successfully!");
        return "redirect:/teacherhome/categories";
    }

    @PostMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        categoryService.deleteCategory(id);
        redirectAttributes.addFlashAttribute("success", "Category deleted successfully!");
        return "redirect:/teacherhome/categories";
    }

    @PostMapping("/delete-categories")
    public String bulkDeleteCategories(@RequestParam(value = "categoryIds", required = false) List<Long> categoryIds, RedirectAttributes redirectAttributes) {
        if (categoryIds != null && !categoryIds.isEmpty()) {
            categoryService.deleteCategories(categoryIds);
            redirectAttributes.addFlashAttribute("success", "Selected categories deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("warning", "No categories selected for deletion.");
        }
        return "redirect:/teacherhome/categories";
    }

    // ============================================
    // QUIZ MANAGEMENT
    // ============================================
    @GetMapping("/quizzes")
    public  String viewQuizzes(Model model) {
        Set<Quiz> quiz = quizService.getQuizes();
        model.addAttribute("quiz", quiz);
        model.addAttribute("activePage", "quizzes");
        return "teacher/quizzes";
    }
   

    @GetMapping("/add-quiz")
    public String showAddQuizForm(Model model) {
        model.addAttribute("quiz", new Quiz());
        model.addAttribute("categories", categoryService.getCategories());
        return "teacher/add-quiz";
    }

    @PostMapping("/save-quiz")
    public String saveQuiz(@ModelAttribute("quiz") Quiz quiz, RedirectAttributes redirectAttributes) {
        quizService.addQuiz(quiz);
        redirectAttributes.addFlashAttribute("success", "Quiz added successfully!");
        return "redirect:/teacherhome/quizzes";
    }

    @GetMapping("/edit-quiz/{id}")
    public String showEditQuizForm(@PathVariable("id") Long id, Model model) {
        Quiz quiz = quizService.getQuiz(id);
        if (quiz == null) {
            return "redirect:/teacherhome/quizzes";
        }
        model.addAttribute("quiz", quiz);
        model.addAttribute("categories", categoryService.getCategories());
        return "teacher/add-quiz";
    }

    @PostMapping("/delete-quiz/{id}")
    public String deleteQuiz(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        quizService.deleteQuiz(id);
        redirectAttributes.addFlashAttribute("success", "Quiz deleted successfully!");
        return "redirect:/teacherhome/quizzes";
    }

    @DeleteMapping("/delete-quizzes")
    public String bulkDeleteQuizzes(@RequestParam(value = "quizIds", required = false) List<Long> quizIds, RedirectAttributes redirectAttributes) {
        if (quizIds != null && !quizIds.isEmpty()) {
            quizService.deleteQuizzes(quizIds);
            redirectAttributes.addFlashAttribute("success", "Selected quizzes deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("warning", "No quizzes selected for deletion.");
        }
        return "redirect:/teacherhome/quizzes";
    }
    @GetMapping("/questions/{id}")
    public String showQuestions(@PathVariable("id") Long quizId, Model model) {
        // Fetch quiz details and associated questions using quizId
        Quiz quiz = quizService.getQuiz(quizId); // Assuming quizService exists
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", quiz.getQuestion()); // Assuming quiz has a list of questions

        return "teacher/questions"; // This should match your Thymeleaf template name
    }

    // ============================================
    // QUESTION MANAGEMENT
    // ============================================

    @GetMapping("/questions")
    public String viewQuestions(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Question> questionPage = questionService.getPaginatedQuestions(page, 10);
        model.addAttribute("questionPage", questionPage);
        model.addAttribute("activePage", "questions ");
        return "teacher/questions";
    }

    @GetMapping("/add-question")
    public String showAddQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        model.addAttribute("quizzes", quizService.getQuizes());
        return "teacher/add-question";
    }

    @PostMapping("/save-question")
    public String saveQuestion(@ModelAttribute("question") Question question, RedirectAttributes redirectAttributes) {
        questionService.addQuestion(question);
        redirectAttributes.addFlashAttribute("success", "Question added successfully!");
        return "redirect:/teacherhome/questions";
    }

    @GetMapping("/edit-question/{id}")
    public String showEditQuestionForm(@PathVariable("id") Long id, Model model) {
        Question question = questionService.getQuestion(id);
        if (question == null) {
            return "redirect:/teacherhome/questions";
        }
        model.addAttribute("question", question);
        model.addAttribute("quizzes", quizService.getQuizes());
        return "teacher/add-question";
    }
 // Delete a single question (GET request, called from link)
    @GetMapping("/delete-question/{id}")
    public String deleteQuestion(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        questionService.deleteQuestion(id);
        redirectAttributes.addFlashAttribute("success", "Question deleted successfully!");
        return "redirect:/teacherhome/questions";
    }


   /* @PostMapping("/delete-question")
    public String deleteQuestion(@RequestParam(value = "quesid", required = false) List<Long> quesid, RedirectAttributes redirectAttributes) {
        if (quesid != null && !quesid.isEmpty()) {
            questionService.deleteQuestion(quesid);
            redirectAttributes.addFlashAttribute("success", "Question(s) deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("warning", "No question selected for deletion.");
        }
        return "redirect:/teacherhome/questions";
    }*/


    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "An error occurred: " + e.getMessage());
        return "redirect:/teacherhome";
    }
}
