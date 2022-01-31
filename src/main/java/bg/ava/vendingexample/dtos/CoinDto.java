package bg.ava.vendingexample.dtos;

import bg.ava.vendingexample.enums.Coin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoinDto {

    private BigDecimal amount;
}
