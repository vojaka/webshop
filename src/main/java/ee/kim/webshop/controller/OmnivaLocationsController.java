package ee.kim.webshop.controller;

import ee.kim.webshop.model.entity.OmnivaLocation;
import ee.kim.webshop.model.request.input.OmnivaLocationsResponse;
import ee.kim.webshop.repository.OmnivaLocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OmnivaLocationsController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OmnivaLocationsRepository omnivaLocationsRepository;

    @Value("${omniva.url}")
    private String omnivaurl;

    List<OmnivaLocationsResponse> omnivaLocations;

    @GetMapping("locations")
    public List<OmnivaLocationsResponse> getData() {
        ResponseEntity <List<OmnivaLocationsResponse>> responseEntity =
                restTemplate.exchange(
                        omnivaurl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<OmnivaLocationsResponse>>() {}
                );
        omnivaLocations = responseEntity.getBody();
        return omnivaLocations;
    }

//    @PostMapping("locations")
//    public void saveOmnivaLocationsToDb(){
////        OmnivaLocation omnivaLocation = new OmnivaLocation();
////        omnivaLocation = omnivaLocations;
//        omnivaLocationsRepository.saveAll(omnivaLocations);
//    }
}
