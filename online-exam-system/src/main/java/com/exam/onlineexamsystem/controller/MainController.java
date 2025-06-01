package com.exam.onlineexamsystem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exam.onlineexamsystem.dto.studentDto;
import com.exam.onlineexamsystem.model.studentModel;
import com.exam.onlineexamsystem.model.teacherModel;
import com.exam.onlineexamsystem.repository.studentRepository;
import com.exam.onlineexamsystem.repository.teacherRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Value("${admin.username}")
	private String adminUsername;

	@Value("${admin.password}")
	private String adminPassword;

	@Autowired
	private studentRepository studentRepo;

	@Autowired
	private teacherRepository teacherRepo;

	// Show homepage
	@GetMapping("/")
	public String showIndex() {
		return "Index";
	}

	// Show login page
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}

	// Handle login for students, teachers, and admin
	@PostMapping("/login")
	public String validateUser(@RequestParam String rollno, @RequestParam String password, @RequestParam String role,
			RedirectAttributes redirectAttributes, HttpSession session) {

		if ("student".equalsIgnoreCase(role)) {
			Optional<studentModel> studentOptional = studentRepo.findById(rollno);

			if (studentOptional.isPresent() && studentOptional.get().getPassword().equals(password)) {
				
				session.setAttribute("userRole", "student");
				session.setAttribute("userId", rollno);
				
				return "redirect:/studenthome";
			} else {
				redirectAttributes.addFlashAttribute("msg", "Invalid student credentials");
			}
		}

		else if ("teacher".equalsIgnoreCase(role)) {
			Optional<teacherModel> teacherOptional = teacherRepo.findById(rollno);

			if (teacherOptional.isPresent() && teacherOptional.get().getPassword().equals(password)) {
				session.setAttribute("userRole", "teacher");
				session.setAttribute("userId", rollno);
				return "redirect:/teacherhome";
			} else {
				redirectAttributes.addFlashAttribute("msg", "Invalid teacher credentials");
			}
		}

		else if ("admin".equalsIgnoreCase(role)) {
			if (rollno.equals(adminUsername) && password.equals(adminPassword)) {
				session.setAttribute("userRole", "admin");
				session.setAttribute("userId", "admin");
				return "redirect:/adminhome";
			} else {
				redirectAttributes.addFlashAttribute("msg", "Invalid admin credentials");
			}
		}

		return "redirect:/login"; // Redirect back to login page if authentication fails
	}

	// Show registration page
	@GetMapping("/registration")
	public String showRegistration(Model model) {
		model.addAttribute("sDto", new studentDto());
		return "Registration";
	}

	// Handle student and teacher registration
	@PostMapping("/registration")
	public String createRegistration(@ModelAttribute studentDto studentDto, RedirectAttributes redirectAttributes) {
		String role = studentDto.getRole();
		String rollno = studentDto.getRollno();

		if ("student".equalsIgnoreCase(role)) {
			if (studentRepo.existsById(rollno)) {
				redirectAttributes.addFlashAttribute("msg", "Student already registered!");
			} else {
				studentModel student = new studentModel();
				student.setRollno(studentDto.getRollno());
				student.setName(studentDto.getName());
				student.setPassword(studentDto.getPassword());
				studentRepo.save(student);
				redirectAttributes.addFlashAttribute("msg", "Student registered successfully!");
			}
		}

		else if ("teacher".equalsIgnoreCase(role)) {
			if (teacherRepo.existsById(rollno)) {
				redirectAttributes.addFlashAttribute("msg", "Teacher already registered!");
			} else {
				teacherModel teacher = new teacherModel();
				teacher.setRollno(studentDto.getRollno());
				teacher.setName(studentDto.getName());
				teacher.setPassword(studentDto.getPassword());
				teacherRepo.save(teacher);
				redirectAttributes.addFlashAttribute("msg", "Teacher registered successfully!");
			}
		}

		return "redirect:/registration"; // Redirect back to the registration page
	}
}
