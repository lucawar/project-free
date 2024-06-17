package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Ordini")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long userId;

    private String product;

    private int quantity;


}
