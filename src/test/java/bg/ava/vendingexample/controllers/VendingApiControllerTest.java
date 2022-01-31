package bg.ava.vendingexample.controllers;

import bg.ava.vendingexample.dtos.CoinDto;
import bg.ava.vendingexample.mappers.ProductMapper;
import bg.ava.vendingexample.services.VendingMachineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VendingApiController.class)
public class VendingApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VendingMachineService service;

    @MockBean
    private ProductMapper mapper;

    @Test
    void whenValidUrlAndMethodAndContentTypeThenReturns200() throws Exception {

        CoinDto coinDto = new CoinDto(new BigDecimal(0.10));

        mockMvc.perform(put("/api/coins")
                        .content(objectMapper.writeValueAsString(coinDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());

    }

    @Test
    void whenValidInputThenMapsToBusinessModel() throws Exception {

        CoinDto coinDto = new CoinDto(new BigDecimal("0.10"));

        mockMvc.perform(put("/api/coins")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(coinDto)))
                .andExpect(status().isOk());

        ArgumentCaptor<BigDecimal> coin = ArgumentCaptor.forClass(BigDecimal.class);
        verify(service, times(1)).insertCoin(coin.capture());
        assertThat(coin.getValue()).isEqualTo(new BigDecimal("0.10"));
    }

    @Test
    void whenValidInputThenReturnSumOfInsertedCoins() throws Exception {

        CoinDto coinDto = new CoinDto(new BigDecimal("0.10"));

        when(service.insertCoin(coinDto.getAmount())).thenReturn(coinDto.getAmount());

        MvcResult mvcResult = mockMvc.perform(put("/api/coins")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(coinDto)))
                .andExpect(status().isOk())
                .andReturn();

        CoinDto expectedResponseBody = coinDto;
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(expectedResponseBody));
    }
}

