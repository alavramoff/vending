package bg.ava.vendingexample.models;

import bg.ava.vendingexample.enums.ProductLine;
import bg.ava.vendingexample.enums.ProductType;
import bg.ava.vendingexample.validators.PriceValidation;
import bg.ava.vendingexample.validators.QuantityValidation;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Product {

    private String name;
    private String description;

    @QuantityValidation
    private Integer quantity;
    private ProductLine productLine;
    private ProductType productType;

    @PriceValidation
    private BigDecimal price;
}
