package ee.kim.webshop.controller;

import ee.kim.webshop.model.entity.Product;
import ee.kim.webshop.model.request.input.CartProduct;
import ee.kim.webshop.model.request.output.EveryPayLink;
import ee.kim.webshop.model.request.output.EveryPayPaymentCheck;
import ee.kim.webshop.repository.OrderRepository;
import ee.kim.webshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {



    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PaymentService paymentService;

    @PostMapping("payment")
    public ResponseEntity<EveryPayLink> getPaymentLink(@RequestBody List<CartProduct> products) {

        List<Product> productsFromDb = paymentService.getProductsFromDb(products);
        double orderSum = paymentService.getOrderSum(productsFromDb);
        Long orderId = paymentService.saveOrderToDb(orderSum,productsFromDb);
        return ResponseEntity.ok()
                .body(paymentService.getPaymentLinkFromEveryPay(orderSum,orderId));
    }

    @PostMapping("check-payment")
    public ResponseEntity<Boolean> checkPayment(@RequestBody EveryPayPaymentCheck everyPayPaymentCheck) {
        Boolean isPaid = paymentService.checkIfOrderPaid(everyPayPaymentCheck);

        return ResponseEntity.ok()
                .body(true);
    }
}
