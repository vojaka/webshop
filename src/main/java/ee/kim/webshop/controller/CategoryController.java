package ee.kim.webshop.controller;


import ee.kim.webshop.model.entity.Category;
import ee.kim.webshop.repository.CategoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;


    @GetMapping("category")
    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }

    @GetMapping("category/{id}")
    public Category viewCategory(@PathVariable Long id){
        Category category = categoryRepository.findById(id).get();
        return category;
    }

    @PostMapping("category")
    public String addCategory(@RequestBody Category category){
        categoryRepository.save(category);
        return "New category added> " + category.getName();
    }

    @DeleteMapping("category/{id}")
    public List<Category> deleteCategory(@PathVariable Long id){
        categoryRepository.deleteById(id);
        return categoryRepository.findAll();
    }

    @DeleteMapping("category")
    public String deleteAllCategory(){
        categoryRepository.flush();
        return "All categories deleted";
    }

    @PutMapping("category/{id}")
    public String editStore(@PathVariable Long id, @RequestBody Category store){
        categoryRepository.save(store);
        return "Category successfully edited:" + id;
    }

//    @PutMapping("store")
//    public String saveAllProducts( @RequestBody List<Store> stores){
//        storeRepository.saveAll(stores);
//        return "Product successfully saved";
//    }

//    @PatchMapping("increase-quantity")
//    public String increaseProductQuantity(@RequestBody Product product){
//        if (productRepository.findById(product.getId()).isPresent()) {
//            int productQuantity = product.getQuantity();
//            product.setQuantity(++productQuantity);
//            productRepository.save(product);
//            return "Quantity Increased";
//        } else {
//            return "Failure to increase, product not found!";
//        }
//    }
//
//    @PatchMapping("decrease-quantity")
//    public String decreaseProductQuantity(@RequestBody Product product){
//        if (productRepository.findById(product.getId()).isPresent() &&
//                product.getQuantity()>0) {
//            int productQuantity = product.getQuantity();
//            product.setQuantity(--productQuantity);
//            productRepository.save(product);
//            return "Quantity Decreased";
//        } else if (product.getQuantity()<1){
//            return "Quantity is 0";
//        } else {
//            return "Failure to increase, product not found!";
//        }
//    }
}
