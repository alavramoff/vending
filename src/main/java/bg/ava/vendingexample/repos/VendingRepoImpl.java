package bg.ava.vendingexample.repos;

import bg.ava.vendingexample.enums.ProductLine;
import bg.ava.vendingexample.enums.ProductType;
import bg.ava.vendingexample.models.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VendingRepoImpl implements VendingRepo{

    private Map<ProductLine, Product> productDepot;

    @PostConstruct
    public void init() {
        productDepot = new HashMap<>();
        loadProducts();
    }

    private void loadProducts() {
        productDepot.put(ProductLine.ONE, new Product("Snack","Tasty Snack", 10, ProductLine.ONE, ProductType.SNACKS, new BigDecimal("2.5")));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productDepot.values());
    }

    @Override
    public Product findById(Integer id) {
        return productDepot.get(ProductLine.returnProductLineById(id));
    }
    @Override
    public Product save(Product product) {
        return productDepot.put(product.getProductLine(), product);
    }

    @Override
    public void delete(Product product) {
        productDepot.remove(product.getProductLine());
    }
}
