package ee.kim.webshop.model.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"barcode"})})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    private String name;

    @Column(nullable = true)
    private double price;

    @NotNull
    private int quantity;

    @NotBlank
    private String imgSrc;

    @NotNull
    private boolean active;

    @NotBlank
    private String category;

    @NotBlank // mitte "null", mitte "", mitte " "
    private String description;

    @NotNull // mitte "null"
    @Column(unique = true)
    private long barcode;
}
