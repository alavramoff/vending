package bg.ava.vendingexample.repos;

import bg.ava.vendingexample.models.Product;
import org.springframework.context.annotation.Profile;

import java.util.List;

public interface VendingRepo {

    List<Product> findAll();
    Product findById(Integer id);
    Product save(Product product);
    void delete(Product product);
}
