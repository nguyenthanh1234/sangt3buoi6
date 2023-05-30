package com.example.sangt3buoi6.controller;

import com.example.sangt3buoi6.entity.Product;
import com.example.sangt3buoi6.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("products",productService.GetAll());
        return "products/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("product",new Product());
        return "products/create";
    }

    @PostMapping("/create")
    public String create(@Valid Product newProduct, BindingResult result, Model model){
        if(result.hasErrors()){
            return "products/create";
        }
        productService.add(newProduct);
        return "redirect:/products";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Product editProduct = null;
        for(Product product : productService.GetAll()){
            if(product.getId().equals(id)){
                editProduct = product;
            }
        }
        if(editProduct != null){
            model.addAttribute("product",editProduct);
            return "products/edit";
        }else{
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("product") Product updatedProduct, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "products/edit";
        }
        for(int i = 0; i<productService.GetAll().size();i++){
            Product product = productService.GetAll().get(i);
            if(product.getId() == updatedProduct.getId()){
                productService.GetAll().set(i,updatedProduct);
                productService.update(updatedProduct);
                break;
            }
        }
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Iterator<Product> iterator = productService.GetAll().iterator();
        while (iterator.hasNext()){
            Product product = iterator.next();
            if(product.getId() == id){
//                iterator.remove();
                productService.delete(id);
                break;
            }
        }
        return "redirect:/products";
    }
}
