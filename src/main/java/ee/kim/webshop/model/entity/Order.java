package ee.kim.webshop.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
@SequenceGenerator(name = "seq", initialValue = 120000)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq")
    private long id;
    private Date timeStamp;
    private double sum;
    private boolean paid;
    @ManyToMany
    private List<Product> orderProducts;
}
