package com.example.sangt3buoi6.service;

import com.example.sangt3buoi6.entity.Product;
import com.example.sangt3buoi6.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;
    public List<Product> GetAll(){
        return (List<Product>) repo.findAll();
    }

    public void add(Product newProduct){
        repo.save(newProduct);
    }

    public void update(Product product){
        repo.save(product);
    }

    public void delete(Integer id){

        repo.deleteById(id);
    }
}
