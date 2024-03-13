package com.example.minitest2.service.impl;

import com.example.minitest2.model.dto.TotalAmountOfCategory;
import com.example.minitest2.model.entity.Category;
import com.example.minitest2.repository.ICategoryRepository;
import com.example.minitest2.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
       return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
       categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
       return categoryRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);

    }

    public Iterable<TotalAmountOfCategory> totalAmountOfCategories() {
        return categoryRepository.sumAmountByCategory();
    }

    public void setNullCategory(Long id) {
        categoryRepository.setNullCategoryIdForTask(id);
    }
}
