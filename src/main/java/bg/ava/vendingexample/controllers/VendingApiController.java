package bg.ava.vendingexample.controllers;


import bg.ava.vendingexample.dtos.CoinDto;
import bg.ava.vendingexample.dtos.ProductDto;
import bg.ava.vendingexample.mappers.ProductMapper;
import bg.ava.vendingexample.models.Product;
import bg.ava.vendingexample.services.VendingMachineService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class VendingApiController {

    private VendingMachineService service;
    private ProductMapper mapper;
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getAllProducts() {
        return mapper.returnListOfMappedProducts(service.showAllAvailableProducts());
    }

    @PutMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto buyProduct(@PathVariable Integer id) {
        return mapper.returnMappedDto(service.orderProduct(id));
    }

    @PutMapping (value = "/coins", produces = MediaType.APPLICATION_JSON_VALUE)
    public CoinDto insertCoin(@RequestBody CoinDto coin) {
        coin.setAmount(service.insertCoin(coin.getAmount()));
        return coin;
    }
}
