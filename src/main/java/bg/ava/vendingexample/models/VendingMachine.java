package bg.ava.vendingexample.models;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VendingMachine {
    private BigDecimal currentAmount;
    private BigDecimal totalTurnover;
}
