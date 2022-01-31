package bg.ava.vendingexample.controllers;

import bg.ava.vendingexample.dtos.CoinDto;
import bg.ava.vendingexample.dtos.ProductDto;
import bg.ava.vendingexample.mappers.ProductMapper;
import bg.ava.vendingexample.models.Product;
import bg.ava.vendingexample.services.VendingMachineService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class VendingApiAdminController {

    private VendingMachineService service;
    private ProductMapper mapper;

    @PutMapping(path = "/collect", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CoinDto collectAll() {
        return new CoinDto(service.collectTurnover());
    }

    @DeleteMapping(path="/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProductFromSlot(@PathVariable Integer id){
        service.deleteProduct(id);
    }

    @PostMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> createOrUpdateProduct(@RequestBody @Valid ProductDto product) {
        service.loadProduct(mapper.returnProductFromDto(product));
        return mapper.returnListOfMappedProducts(service.showAllAvailableProducts());
    }
}
