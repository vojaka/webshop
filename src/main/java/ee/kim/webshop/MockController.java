package ee.kim.webshop;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MockController {
    List<String> strings = new ArrayList<>(Arrays.asList("one","two"));

    //GET localhost:8080/strings

    @GetMapping("strings")
    public List<String> getStrings(){
        return strings;
    }

    @PostMapping("strings")
    public String addStrings(@RequestBody String newString){
        strings.add(newString);
        return "Successfully added: " + newString;
    }

   @DeleteMapping("strings/{id}")
   public String deleteStrings(@PathVariable int id){
        strings.remove(id);
         return "Successfully deleted";
   }

   @DeleteMapping()
    public String deleteAllStrings(){
        strings.clear();
        return "Successfully deleted all strings";
    }

    @PutMapping("string/{id}")
    public String editString(@PathVariable int id, @RequestBody String string){
        strings.set(id,string);
        return "Successfully edited:" + id;
    }

}
