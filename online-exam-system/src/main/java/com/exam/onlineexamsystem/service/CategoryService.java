package com.exam.onlineexamsystem.service;

import java.util.List;
import java.util.Set;

import com.exam.onlineexamsystem.model.exam.Category;

public interface CategoryService {
	
	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public Category getCategory(Long categoryId);
	
	public Set<Category> getCategories();
	
	public void deleteCategory(Long categoryId);

	public void deleteCategories(List<Long> categoryIds);

}
