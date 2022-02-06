package ee.kim.webshop.controller;

import ee.kim.webshop.model.entity.Product;
import ee.kim.webshop.model.request.input.EveryPayResponse;
import ee.kim.webshop.model.request.output.EveryPayData;
import ee.kim.webshop.model.request.output.EveryPayLink;
import ee.kim.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PaymentController {

    @Value("${everypay.username}")
    private String everyPayUsername;

    @Value("${everypay.url}")
    private String everyPayUrl;

    @Value("${everypay.accountname}")
    private String everyPayAccountname;

    @Value("${everypay.authorization}")
    private String authorization;

    @Value("${everypay.customerurl}")
    private String customerurl;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("payment")
    public EveryPayLink getPaymentLink(@RequestBody List<Product> products) {
        //1 list products
        //arvutame nende pealt summa
//        List<Product> productsFromDb = new ArrayList<>();
//        for (Product p: products){
//            Product productFound = productRepository.findById(p.getId()).get();
//            productsFromDb.add(productFound);
//        }

        List<Product> productsFromDb = products.stream()
                .map(e -> productRepository.findById(e.getId()).get())
                .collect(Collectors.toList());

        double orderSum = productsFromDb.stream().mapToDouble(Product::getPrice).sum();

        //2. v]tame igayhel id ja otsime db\st ja v]tame sealt hinda

        //3, teeme tellimuse Entity

        //4. teeme tellimuse Repository
        //5. siit paneme andmebaasi
        //6. p'rast db see panemist, v6tame uuesti ja v]tame id

        Instant now = Instant.now();
        ZonedDateTime timeStamp = ZonedDateTime.ofInstant(now, ZoneId.systemDefault());

        EveryPayData everyPayData = new EveryPayData();
        everyPayData.setApi_username(everyPayUsername);
        everyPayData.setAccount_name(everyPayAccountname);
        everyPayData.setAmount(orderSum);
        everyPayData.setOrder_reference(4321L);
        everyPayData.setTimestamp(timeStamp.toString());
        everyPayData.setNonce(everyPayUsername +4321L + new Date());
        everyPayData.setCustomer_url(customerurl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",authorization);
        HttpEntity<EveryPayData> httpEntity = new HttpEntity<>(everyPayData, headers);
        //m2lur88vel
        //RestTemplate restTemplate = new RestTemplate();
        //p6hiline kood
        ResponseEntity<EveryPayResponse> response = restTemplate.exchange(everyPayUrl, HttpMethod.POST,httpEntity, EveryPayResponse.class);
        System.out.println(response);
        System.out.println(response.getBody());
        EveryPayLink link = new EveryPayLink();

        if (response.getBody()!= null){
            link.setPayment_link(response.getBody().getPayment_link());
        }
        //link.setPayment_link("asas");
        return link;
    }
    //bean
    //config file
    //api
    //saatmise p6hi
    // tegema body header ja need siduma
    // p'ringu tegema netti
    //response
    //
}
