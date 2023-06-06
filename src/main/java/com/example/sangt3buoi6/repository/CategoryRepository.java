package com.example.sangt3buoi6.repository;

import com.example.sangt3buoi6.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Service
    public class PhongBanService {
        @Autowired
        private CategoryRepository categoryRepository;

        public List<Category> GetAll(){
            return categoryRepository.findAll();
        }

    }
}

