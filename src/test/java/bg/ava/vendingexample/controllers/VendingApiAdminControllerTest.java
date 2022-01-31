package bg.ava.vendingexample.controllers;

import bg.ava.vendingexample.dtos.CoinDto;
import bg.ava.vendingexample.mappers.ProductMapper;
import bg.ava.vendingexample.services.VendingMachineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VendingApiAdminController.class)
public class VendingApiAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VendingMachineService service;

    @MockBean
    private ProductMapper mapper;

    @Test
    void whenValidUrlAndMethodAndContentTypeThenReturns401() throws Exception {

        mockMvc.perform(put("/api/admin/collect")
                        .contentType("application/json"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "password", password = "password", roles = "ADMIN")
    void whenValidUrlAndMethodAndContentTypeThenReturnOk() throws Exception {

        mockMvc.perform(put("/api/admin/collect")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "password", password = "password", roles = "ADMIN")
    void whenValidUrlAndMethodAndContentTypeThenReturnCollecteAmount() throws Exception {

        CoinDto coinDto = new CoinDto(new BigDecimal("1.50"));

        when(service.collectTurnover()).thenReturn(coinDto.getAmount());

        MvcResult result = mockMvc.perform(put("/api/admin/collect")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        CoinDto expectedResponseBody = coinDto;
        String actualResponseBody = result.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(expectedResponseBody));
    }
}