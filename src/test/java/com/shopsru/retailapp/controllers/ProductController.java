package com.shopsru.retailapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopsru.retailapp.controllers.ProductController;
import com.shopsru.retailapp.model.Product;
import com.shopsru.retailapp.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService productService;
    private Product product;
    private List<Product> productList;
    @InjectMocks
    private ProductController productController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        product = new Product(1L,"Milo", "grocery",2500);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @AfterEach
    void tearDown() {
        product = null;
    }

    @Test
    public void postMappingOfProduct() throws Exception{
        when(productService.createProduct(any())).thenReturn(product);
        mockMvc.perform(post("/api/products/add").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(product))).
                andExpect(status().isOk());
        verify(productService,times(1)).createProduct(any());
    }

    @Test
    public void getMappingOfAllProduct() throws Exception {
        when(productService.getAllProducts()).thenReturn(productList);
        mockMvc.perform(get("/api/products").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(product))).
                andDo(MockMvcResultHandlers.print());
        verify(productService).getAllProducts();
        verify(productService,times(1)).getAllProducts();
    }

    @Test
    public void getMappingOfProductShouldReturnRespectiveProduct() throws Exception {
        when(productService.getProductById(product.getId())).thenReturn(product);
        mockMvc.perform(get("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMappingUrlAndIdThenShouldReturnDeletedProduct() throws Exception {
        when(productService.deleteProduct(product.getId())).thenReturn(product);
        mockMvc.perform(delete("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
