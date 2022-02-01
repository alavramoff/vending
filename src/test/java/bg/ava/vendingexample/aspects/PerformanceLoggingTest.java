package bg.ava.vendingexample.aspects;

import bg.ava.vendingexample.configuration.AspectConfig;
import bg.ava.vendingexample.controllers.VendingApiController;
import bg.ava.vendingexample.dtos.CoinDto;
import bg.ava.vendingexample.mappers.ProductMapper;
import bg.ava.vendingexample.services.VendingMachineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith({OutputCaptureExtension.class, SpringExtension.class})
@WebMvcTest(controllers = VendingApiController.class)
@ContextConfiguration(classes = { AspectConfig.class })
@EnableAutoConfiguration
public class PerformanceLoggingTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VendingMachineService service;

    @MockBean
    private ProductMapper mapper;

    @Autowired
    VendingApiController controller;

    @Test
    @DirtiesContext
    public void testLogger(CapturedOutput capture) throws Exception {
        CoinDto coinDto = new CoinDto(new BigDecimal(0.10));
        controller.insertCoin(coinDto);

        // AOP VERIFICATION
        // LoggingAspect should have output an INFO message to console
        String consoleOutput = capture.getOut();
        assertTrue(consoleOutput.contains("INFO"));
        assertTrue(consoleOutput.contains("Execution time of "));
    }
}