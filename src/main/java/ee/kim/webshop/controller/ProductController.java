package ee.kim.webshop.controller;

import ee.kim.webshop.repository.OrderRepository;
import ee.kim.webshop.repository.ProductRepository;
import ee.kim.webshop.model.entity.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("products")
    public List<Product> getProducts(){
        log.info("v6eti k6ik tooted");
        log.debug("v6eti k6ik tooted");
        log.error("v6eti k6ik tooted");
        return productRepository.findAll();
    }
    @PostMapping("products")
    public String addProduct(@RequestBody Product product){
        productRepository.save(product);
        return "New product added> " + product.getName();
    }

    @DeleteMapping("products/{id}")
    public String deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return "Deleted product number" + id;
    }

    @DeleteMapping("products")
    public String deleteAllProducts(){
        productRepository.flush();
        return "All products deleted";
    }

    @PutMapping("product/{id}")
    public String editProduct(@PathVariable Long id, @RequestBody Product product){
        productRepository.save(product);
        return "Product successfully edited:" + id;
    }
}
