package ee.kim.webshop.repository;

import ee.kim.webshop.model.entity.Order;
import ee.kim.webshop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
