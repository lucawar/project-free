package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDTO {

    private Long id;

    private Long userId;

    private String product;

    private int quantity;
}
