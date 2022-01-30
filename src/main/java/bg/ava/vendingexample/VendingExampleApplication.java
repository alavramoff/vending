package bg.ava.vendingexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class VendingExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendingExampleApplication.class, args);
    }

}
