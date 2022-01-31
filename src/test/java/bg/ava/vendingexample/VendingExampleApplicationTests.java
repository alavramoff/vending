package bg.ava.vendingexample;

import bg.ava.vendingexample.controllers.VendingApiAdminController;
import bg.ava.vendingexample.controllers.VendingApiController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class VendingExampleApplicationTests {

    @Autowired
    VendingApiController apiController;

    @Autowired
    VendingApiAdminController adminController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(apiController);
        Assertions.assertNotNull(adminController);
    }

}
