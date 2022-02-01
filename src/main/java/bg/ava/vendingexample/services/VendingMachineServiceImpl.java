package bg.ava.vendingexample.services;

import bg.ava.vendingexample.enums.Coin;
import bg.ava.vendingexample.exceptions.AmountNotSufficientException;
import bg.ava.vendingexample.exceptions.CoinNotSupportedException;
import bg.ava.vendingexample.exceptions.ProductNotAvailableException;
import bg.ava.vendingexample.exceptions.QuantityLimitException;
import bg.ava.vendingexample.models.Product;
import bg.ava.vendingexample.models.VendingMachine;
import bg.ava.vendingexample.repos.VendingRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class VendingMachineServiceImpl implements VendingMachineService {

    private VendingRepo vendingRepo;
    private VendingMachine machine;

    @Override
    public List<Product> showAllAvailableProducts() {
        List<Product> list = vendingRepo.findAll().stream().filter( p -> p.getQuantity() > 0 ).collect(Collectors.toList());
        return list;
    }

    @Override
    public Product orderProduct(Integer prodNumber) {
        Product orderedProduct = vendingRepo.findById(prodNumber);
        if(orderedProduct == null || orderedProduct.getQuantity() < 1) {
            throw new ProductNotAvailableException();
        }
        if(machine.getCurrentAmount().compareTo(orderedProduct.getPrice()) > 0) {
            orderedProduct.setQuantity(orderedProduct.getQuantity() - 1);
            machine.setTotalTurnover(machine.getTotalTurnover().add(machine.getCurrentAmount()));
            machine.setCurrentAmount(new BigDecimal(0.00));
            return vendingRepo.save(orderedProduct);
        } else {
            throw new AmountNotSufficientException();
        }
    }

    @Override
    public BigDecimal insertCoin(BigDecimal coinAmount) {
        Coin realCoin = Arrays.stream(Coin.values()).filter(c -> c.getValue().compareTo(coinAmount) == 0).findFirst().orElseThrow(() -> new CoinNotSupportedException());
        machine.setCurrentAmount(machine.getCurrentAmount().add(realCoin.getValue()));
        return machine.getCurrentAmount();
    }

    @Override
    public BigDecimal resetCoin() {
        machine.setCurrentAmount(new BigDecimal(0.00));
        return machine.getCurrentAmount();
    }

    @Override
    public BigDecimal collectTurnover() {
        BigDecimal collect = machine.getTotalTurnover();
        machine.setTotalTurnover(new BigDecimal(0.00));
        return  collect;
    }

    @Override
    public Product loadProduct(Product product) {
        Integer quantity = product.getQuantity();
        Product availableProduct = vendingRepo.findById(Integer.valueOf(product.getProductLine().ordinal() + 1));

        if(availableProduct != null && ((quantity + availableProduct.getQuantity()) <= 10)) {
            product.setQuantity(quantity + availableProduct.getQuantity());
            return vendingRepo.save(product);
        }
        if(availableProduct == null && quantity <= 10) {
            return vendingRepo.save(product);
        }
        throw new QuantityLimitException();
    }

    @Override
    public void deleteProduct(Integer id) {
        vendingRepo.delete(vendingRepo.findById(id));
    }
}
