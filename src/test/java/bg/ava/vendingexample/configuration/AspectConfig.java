package bg.ava.vendingexample.configuration;

import bg.ava.vendingexample.controllers.VendingApiController;
import bg.ava.vendingexample.mappers.ProductMapper;
import bg.ava.vendingexample.models.VendingMachine;
import bg.ava.vendingexample.repos.VendingRepo;
import bg.ava.vendingexample.repos.VendingRepoImpl;
import bg.ava.vendingexample.services.VendingMachineService;
import bg.ava.vendingexample.services.VendingMachineServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "bg.ava.vendingexample.aspects")
@EnableAspectJAutoProxy
public class AspectConfig {

    @Bean
    public VendingApiController returnController(ProductMapper mapper,VendingMachineService service) {
        return new VendingApiController(service, mapper);
    }

    @Bean
    public ProductMapper returnMapper() {
        return new ProductMapper();
    }

    @Bean
    public VendingMachineService returnService(VendingRepo repo, VendingMachine vendingMachine) {
        return new VendingMachineServiceImpl(repo, vendingMachine);
    }

    @Bean
    public VendingRepo returnRepo() {
        return new VendingRepoImpl();
    }
}