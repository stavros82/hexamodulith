package com.example.stock.adapters.rest;

import com.example.stock.adapters.config.StockTestConfig;
import com.example.stock.adapters.service.TransactionalStockUpdateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StockTestConfig.class)
@AutoConfigureMockMvc
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionalStockUpdateService transactionalStockUpdateService;

    @Test
    void shouldUpdateStockSuccessfully() throws Exception {
        Integer itemId = 1;
        int quantity = 10;
        boolean reorderNeeded = false;

        mockMvc.perform(post("/stock/{itemId}/update/{quantity}/{reorderNeeded}", itemId, quantity, reorderNeeded)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(transactionalStockUpdateService, Mockito.times(1)).handle(Mockito.any());
    }
}
