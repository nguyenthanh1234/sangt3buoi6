package com.example.sangt3buoi6.controller;

import com.example.sangt3buoi6.entity.Product;
import com.example.sangt3buoi6.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";
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
    public String create(@Valid Product newProduct, @RequestParam("imageProduct") MultipartFile imageProduct
            , BindingResult result,  Model model) throws IOException{
        if(result.hasErrors()){
            model.addAttribute("product",newProduct);
            return "products/create";
        }
        if(imageProduct != null && imageProduct.getSize() > 0) {
                StringBuilder fileNames = new StringBuilder();
                Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, imageProduct.getOriginalFilename());
                fileNames.append(imageProduct.getOriginalFilename());
                Files.write(fileNameAndPath, imageProduct.getBytes());
                newProduct.setImage(fileNames.toString());
        }
//        if(imageProduct != null && imageProduct.getSize() > 0){
//            try{
//                File saveFile = new ClassPathResource("static/anh").getFile();
//                String newImageFile = UUID.randomUUID() + ".png";
//                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + newImageFile);
//                Files.copy(imageProduct.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
        //
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
    public String edit(@Valid @ModelAttribute("product") Product updatedProduct
            ,@RequestParam("imageProduct") MultipartFile imageProduct, BindingResult bindingResult, Model model)throws IOException{
        if(bindingResult.hasErrors()){
            return "products/edit";
        }
        if(imageProduct != null && imageProduct.getSize() > 0) {
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, imageProduct.getOriginalFilename());
            fileNames.append(imageProduct.getOriginalFilename());
            Files.write(fileNameAndPath, imageProduct.getBytes());
            updatedProduct.setImage(fileNames.toString());
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
