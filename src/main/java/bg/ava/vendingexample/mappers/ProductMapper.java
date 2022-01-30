package bg.ava.vendingexample.mappers;

import bg.ava.vendingexample.dtos.ProductDto;
import bg.ava.vendingexample.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    public ProductDto returnMappedDto(Product product) {
        return new ProductDto(product);
    }

    public List<ProductDto> returnListOfMappedProducts(List<Product> productList) {
        var list = new ArrayList<ProductDto>();
        productList.forEach(c -> list.add(new ProductDto(c)));
        return list;
    }

    public Product returnProductFromDto(ProductDto productDto) {
        Product product = new Product();
        product.setQuantity(productDto.getQuantity());
        product.setProductLine(productDto.getProductLine());
        product.setProductType(productDto.getProductType());
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        return product;
    }



}
