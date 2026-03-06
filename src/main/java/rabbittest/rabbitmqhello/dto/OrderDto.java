package rabbittest.rabbitmqhello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {

    private String orderId;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private LocalDateTime orderTime;

}
