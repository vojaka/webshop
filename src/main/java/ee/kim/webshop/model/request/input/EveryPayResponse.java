package ee.kim.webshop.model.request.input;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class EveryPayResponse {
    private String api_username;
    private String account_name;
    private double amount;
    private Long order_reference;
    private Date timestamp;
    private String nonce;
    private String customer_url;
    private String email;
    private String customer_ip;
    private String payment_created_at;
    private BigDecimal initial_amount;
    private BigDecimal standing_amount;
    private String payment_reference;
    private String payment_link;
    private List<PaymentMethod> payment_methods;
    private String stan;
    private String fraud_score;
    private String payment_state;
    private String payment_method;
}

@Data
class PaymentMethod {
    private String source;
    private String displayName;
    private String countryCode;
    private String paymentLink;
    private String logoUrl;
}
