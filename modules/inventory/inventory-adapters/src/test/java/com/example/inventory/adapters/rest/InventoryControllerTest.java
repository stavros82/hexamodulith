package com.example.inventory.adapters.rest;

import com.example.inventory.adapters.config.InventoryTestConfig;
import com.example.inventory.adapters.service.TransactionalCreateInventoryService;
import com.example.inventory.adapters.service.TransactionalDecreaseStockService;
import com.example.inventory.domain.model.InventoryItem;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = InventoryTestConfig.class)
@AutoConfigureMockMvc


class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private TransactionalDecreaseStockService decreaseStockService;

    @MockBean
    private TransactionalCreateInventoryService createInventoryService;

    @Test
    void shouldDecreaseStockSuccessfully() throws Exception {
        // Given
        int itemId = 123;
        int amount = 5;
        int remainingQty = 15;

        // Mock behavior of the decreaseStockService
        Mockito.when(decreaseStockService.handle(itemId, amount)).thenReturn(remainingQty);

        // When & Then
        mockMvc.perform(post("/inventory/{itemId}/decrease/{amount}", itemId, amount)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(remainingQty));
    }

    @Test
    void shouldCreateInventoryItemSuccessfully() throws Exception {
        // Given
        String itemName = "Widget";

        // Mocking domain model
        InventoryItem mockItem = new InventoryItem(itemName);


        Mockito.when(createInventoryService.createItem(anyString())).thenReturn(mockItem);

        // When & Then
        mockMvc.perform(post("/inventory/create/{name}", itemName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemId").value(mockItem.getItemId()))
                .andExpect(jsonPath("$.name").value(mockItem.getName())); // Check multiple fields if appropriate
    }


}