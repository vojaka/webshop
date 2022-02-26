package ee.kim.webshop.controller;


import ee.kim.webshop.model.entity.Store;
import ee.kim.webshop.repository.StoreRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:3000")
public class StoreController {

    @Autowired
    StoreRepository storeRepository;

    @GetMapping("store")
    public List<Store> getStores(){
        return storeRepository.findAll();
    }

    @GetMapping("store/{id}")
    public Store viewStore(@PathVariable Long id){
        Store store = storeRepository.findById(id).get();
        return store;
    }

    @PostMapping("store")
    public String addStore(@RequestBody Store store){
        storeRepository.save(store);
        return "New product added> " + store.getName();
    }

    @DeleteMapping("store/{id}")
    public List<Store> deleteStore(@PathVariable Long id){
        storeRepository.deleteById(id);
        return storeRepository.findAll();
    }

    @DeleteMapping("store")
    public String deleteAllStores(){
        storeRepository.flush();
        return "All products deleted";
    }

    @PutMapping("store/{id}")
    public String editStore(@PathVariable Long id, @RequestBody Store store){
        storeRepository.save(store);
        return "Product successfully edited:" + id;
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
