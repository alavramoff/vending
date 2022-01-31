package bg.ava.vendingexample.controllers;


import bg.ava.vendingexample.dtos.CoinDto;
import bg.ava.vendingexample.dtos.ProductDto;
import bg.ava.vendingexample.mappers.ProductMapper;
import bg.ava.vendingexample.services.VendingMachineService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAllProducts() {
        return mapper.returnListOfMappedProducts(service.showAllAvailableProducts());
    }

    @PutMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductDto buyProduct(@PathVariable Integer id) {
        return mapper.returnMappedDto(service.orderProduct(id));
    }

    @PutMapping (value = "/coins", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CoinDto insertCoin(@RequestBody CoinDto coin) {
        CoinDto dto = new CoinDto(service.insertCoin(coin.getAmount()));
        return dto;
    }
}
