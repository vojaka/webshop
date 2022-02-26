package ee.kim.webshop.controller;

import ee.kim.webshop.repository.OrderRepository;
import ee.kim.webshop.repository.ProductRepository;
import ee.kim.webshop.model.entity.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return productRepository.findByOrderByIdAsc();
    }

    @GetMapping("products/{id}")
    public Product viewProduct(@PathVariable Long id){
        Product product = productRepository.findById(id).get();
        return product;
    }

    @PostMapping("products")
    public String addProduct(@RequestBody Product product){
        productRepository.save(product);
        return "New product added> " + product.getName();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return productRepository.findAll();
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

    @PutMapping("products")
    public String saveAllProducts( @RequestBody List<Product> product){
        productRepository.saveAll(product);
        return "Product successfully saved";
    }

    @PatchMapping("increase-quantity")
    public ResponseEntity<String> increaseProductQuantity(@RequestBody Product product) {
        if (productRepository.findById(product.getId()).isPresent()) {
            int productQuantity = product.getQuantity();
            product.setQuantity(++productQuantity);
            productRepository.save(product);
            return ResponseEntity.ok()
                    .body("Edukalt toote kogus suurendatud");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Toodet mille kogust taheti muuta ei leitud");
        }
    }

    @PatchMapping("decrease-quantity")
    public ResponseEntity<String> decreaseProductQuantity(@RequestBody Product product) {
        if (productRepository.findById(product.getId()).isPresent() &&
                product.getQuantity() > 0) {
            int productQuantity = product.getQuantity();
            product.setQuantity(--productQuantity);
            productRepository.save(product);
            return ResponseEntity.ok()
                    .body("Edukalt toote kogus vähendatud");

        } else if (product.getQuantity() < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tootel mille kogust taheti vähendada on kogus liiga väike");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Toodet mille kogust taheti muuta ei leitud");
        }

    }
}
