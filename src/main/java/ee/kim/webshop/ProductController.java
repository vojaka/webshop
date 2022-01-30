package ee.kim.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @PostMapping("products")
    public String addProduct(@RequestBody Product product){
        productRepository.save(product);
        return "New product added" + product.getName();
    }

    @DeleteMapping("products/{id}")
    public String deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return "Deleted product number" + id;
    }

    @DeleteMapping("products")
    public String deleteAllProducts(){
        productRepository.flush();
        return "All products deleted product number";
    }

    @PutMapping("product/{id}")
    public String editProduct(@PathVariable Long id, @RequestBody Product product){
        productRepository.save(product);
        return "Product successfully edited:" + id;
    }
}
