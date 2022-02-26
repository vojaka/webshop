package ee.kim.webshop.service;

import ee.kim.webshop.model.entity.Order;
import ee.kim.webshop.model.entity.Product;
import ee.kim.webshop.model.request.input.CartProduct;
import ee.kim.webshop.model.request.input.EveryPayResponse;
import ee.kim.webshop.model.request.output.EveryPayData;
import ee.kim.webshop.model.request.output.EveryPayLink;
import ee.kim.webshop.repository.OrderRepository;
import ee.kim.webshop.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class PaymentService {
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

    @Autowired
    OrderRepository orderRepository;

    public List<Product> getProductsFromDb(List<CartProduct> products){
        log.info(products.get(0).getCartProduct().getId());
        return products.stream()
                .map(e -> productRepository.findById(e.getCartProduct().getId()).get())
                .collect(Collectors.toList());
    }
    public double getOrderSum(List<Product> products){
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public Long saveOrderToDb(double orderSum, List<Product> products){
        Order order = new Order();
        order.setTimeStamp(new Date());
        order.setSum(orderSum);
        order.setOrderProducts(products);

        Order updateOrder = orderRepository.save(order);
        return updateOrder.getId();
    }

    public EveryPayLink getPaymentLinkFromEveryPay(double orderSum, Long orderID){
        Instant now = Instant.now();
        ZonedDateTime timeStamp = ZonedDateTime.ofInstant(now, ZoneId.systemDefault());

        EveryPayData everyPayData = new EveryPayData();
        everyPayData.setApi_username(everyPayUsername);
        everyPayData.setAccount_name(everyPayAccountname);
        everyPayData.setAmount(orderSum);
        everyPayData.setOrder_reference(orderID);
        everyPayData.setTimestamp(timeStamp.toString());
        everyPayData.setNonce(everyPayUsername + orderID + new Date());
        everyPayData.setCustomer_url(customerurl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",authorization);
        HttpEntity<EveryPayData> httpEntity = new HttpEntity<>(everyPayData, headers);

        ResponseEntity<EveryPayResponse> response = restTemplate.exchange(everyPayUrl, HttpMethod.POST,httpEntity, EveryPayResponse.class);
        System.out.println(response);
        System.out.println(response.getBody());
        EveryPayLink link = new EveryPayLink();

        if (response.getBody()!= null){
            link.setPayment_link(response.getBody().getPayment_link());
        }
        return link;
    }

}
