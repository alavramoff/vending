package bg.ava.vendingexample.services;

import bg.ava.vendingexample.enums.Coin;
import bg.ava.vendingexample.models.Product;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineService {

    List<Product> showAllAvailableProducts();
    Product orderProduct(Integer prodNumber);
    BigDecimal insertCoin(BigDecimal coin);
    BigDecimal resetCoin();
    BigDecimal collectTurnover();
    Product loadProduct(Product product);
    void deleteProduct(Integer id);
}
