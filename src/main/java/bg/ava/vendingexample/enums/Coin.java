package bg.ava.vendingexample.enums;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum Coin {

    ST_5(new BigDecimal("0.05")),
    ST_10(new BigDecimal("0.1")),
    ST_20(new BigDecimal("0.2")),
    ST_50(new BigDecimal("0.5")),
    LV_1(new BigDecimal("1")),
    LV_2(new BigDecimal("2"));

    private BigDecimal value;

    Coin(BigDecimal val) {
        this.value = val;
    }
}
