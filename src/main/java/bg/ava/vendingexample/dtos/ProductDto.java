package bg.ava.vendingexample.dtos;

import bg.ava.vendingexample.enums.ProductLine;
import bg.ava.vendingexample.enums.ProductType;
import bg.ava.vendingexample.models.Product;
import bg.ava.vendingexample.validators.PriceValidation;
import bg.ava.vendingexample.validators.QuantityValidation;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductDto {

    private String name;

    private String description;

    @QuantityValidation
    private Integer quantity;

    private ProductLine productLine;

    private ProductType productType;

    @PriceValidation
    private BigDecimal price;

    public ProductDto(Product product) {
        this.setQuantity(product.getQuantity());
        this.setProductLine(product.getProductLine());
        this.setProductType(product.getProductType());
        this.setDescription(product.getDescription());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
    }
}
