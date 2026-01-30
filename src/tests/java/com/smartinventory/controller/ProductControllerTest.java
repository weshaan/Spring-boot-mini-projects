package com.smartinventory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartinventory.dto.request.ProductRequest;
import com.smartinventory.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ProductService productService;
    @Autowired private ObjectMapper objectMapper;

    @Test
    void createProduct_success() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setName("Mouse");
        request.setSku("SKU-MOU");
        request.setPrice(500.0);
        request.setCategoryId(1L);

        mockMvc.perform(post("/api/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
}
