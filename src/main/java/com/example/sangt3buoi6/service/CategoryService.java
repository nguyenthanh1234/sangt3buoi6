package com.example.sangt3buoi6.service;

import com.example.sangt3buoi6.entity.Category;
import com.example.sangt3buoi6.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> GetAll(){
        return categoryRepository.findAll();
    }

}