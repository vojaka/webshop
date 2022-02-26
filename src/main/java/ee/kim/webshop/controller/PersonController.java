package ee.kim.webshop.controller;


import ee.kim.webshop.model.entity.Person;
import ee.kim.webshop.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("person")
    public List<Person> getCategory(){
        return personRepository.findAll();
    }

    @GetMapping("person/{id}")
    public Person viewPerson(@PathVariable Long id){
        Person person = personRepository.findById(id).get();
        return person;
    }

    @PostMapping("person")
    public String addPerson(@RequestBody Person person){
        personRepository.save(person);
        return "New person added> " + person.getFirstName() + " " + person.getLastName();
    }

    @DeleteMapping("person/{id}")
    public List<Person> deletePerson(@PathVariable Long id){
        personRepository.deleteById(id);
        return personRepository.findAll();
    }

    @DeleteMapping("person")
    public String deleteAllPersons(){
        personRepository.flush();
        return "All products deleted";
    }

    @PutMapping("person/{id}")
    public String editPerson(@PathVariable Long id, @RequestBody Person person){
        personRepository.save(person);
        return "Person successfully edited:" + id;
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
