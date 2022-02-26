package ee.kim.webshop.model.request.input;

import ee.kim.webshop.model.entity.Product;
import lombok.Data;

@Data
public class CartProduct {
    private Product cartProduct;
    private int quantity;
}
