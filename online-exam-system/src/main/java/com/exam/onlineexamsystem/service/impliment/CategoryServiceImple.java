package com.exam.onlineexamsystem.service.impliment;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.onlineexamsystem.model.exam.Category;
import com.exam.onlineexamsystem.repository.CategoryRepository;
import com.exam.onlineexamsystem.service.CategoryService;


@Service
public class CategoryServiceImple implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		return this.categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		// TODO Auto-generated method stub
		return this.categoryRepository.save(category);
	}

	@Override
	public Category getCategory(Long categoryId) {
		// TODO Auto-generated method stub
		return this.categoryRepository.findById(categoryId).get();
	}

	@Override
	public Set<Category> getCategories() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(this.categoryRepository.findAll());
	}

	@Override
	public void deleteCategory(Long categoryId) {
	    this.categoryRepository.deleteById(categoryId);
	}

	@Override
	public void deleteCategories(List<Long> categoryIds) {
		// TODO Auto-generated method stub
		
	}


}
