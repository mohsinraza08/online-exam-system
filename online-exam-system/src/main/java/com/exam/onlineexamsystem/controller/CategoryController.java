package com.exam.onlineexamsystem.controller;

import com.exam.onlineexamsystem.model.exam.Category;
import com.exam.onlineexamsystem.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")  // Allows cross-origin requests (if needed for frontend)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 1️⃣ Add a new category
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category newCategory = categoryService.addCategory(category);
        return ResponseEntity.ok(newCategory);
    }

    // 2️⃣ Update an existing category
    @PutMapping("/")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(category);
        return ResponseEntity.ok(updatedCategory);
    }

    // 3️⃣ Get a single category by ID
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable Long categoryId) {
        Category category = categoryService.getCategory(categoryId);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    // 4️⃣ Get all categories
    @GetMapping("/")
    public ResponseEntity<Set<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    // 5️⃣ Delete a category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
