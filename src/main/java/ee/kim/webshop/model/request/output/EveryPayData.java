package ee.kim.webshop.model.request.output;

import lombok.Data;

import java.util.Date;

@Data
public class EveryPayData {
    private String api_username;
    private String account_name;
    private double amount;
    private Long order_reference;
    private String timestamp;
    private String nonce;
    private String customer_url;
}
