package bg.ava.vendingexample.configurations;

import bg.ava.vendingexample.models.VendingMachine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class VendingMonetaryConfiguration {

    @Bean
    public VendingMachine ourVendingMachine() {
        return new VendingMachine(new BigDecimal(0.00), new BigDecimal(0.00));
    }

}
