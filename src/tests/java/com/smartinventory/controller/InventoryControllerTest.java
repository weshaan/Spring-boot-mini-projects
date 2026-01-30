package com.smartinventory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartinventory.dto.request.StockInRequest;
import com.smartinventory.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InventoryController.class)
class InventoryControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private InventoryService inventoryService;
    @Autowired private ObjectMapper objectMapper;

    @Test
    void stockIn_success() throws Exception {
        StockInRequest request = new StockInRequest();
        request.setProductId(1L);
        request.setQuantity(5);

        mockMvc.perform(post("/api/inventory/in")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
