package com.exam.onlineexamsystem.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/adminhome")
public class adminController {

	@GetMapping("")
	public String showAdminHome(HttpSession session, Model model, HttpServletResponse response) {
		// Prevent caching for security
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		// Get user role from session safely
		String userRole = Optional.ofNullable((String) session.getAttribute("userRole")).orElse("").toLowerCase();

		// If user is not admin, redirect to login
		if (!"admin".equals(userRole)) {
			return "redirect:/login";
		}

		return "admin/adminhome"; // Ensure "adminhome.html" exists in templates/admin/
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

}
