package com.shopsru.retailapp.service;

import com.shopsru.retailapp.model.Product;
import com.shopsru.retailapp.repositories.ProductRepository;
import com.shopsru.retailapp.serviceImpl.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Autowired
    @InjectMocks
    private ProductServiceImpl productService;
    private Product product1;
    private Product product2;
    List<Product> productList;

    @BeforeEach
    public void setUp() {
        productList = new ArrayList<>();
        product1 = new Product(1L, "Milo", "grocery", 2500);
        product2 = new Product(2L, "Oven", "appliances", 25300);
        productList.add(product1);
        productList.add(product2);
    }

    @AfterEach
    public void tearDown() {
        product1 = product2 = null;
        productList = null;
    }

    @Test
    void givenProductToAddShouldReturnAddedProduct() throws PersistenceException {
        when(productRepository.save(any())).thenReturn(product1);
        productService.createProduct(product1);
        verify(productRepository,times(1)).save(any());
    }

    @Test
    public void GivenGetAllUsersShouldReturnListOfAllUsers(){
        productRepository.save(product1);
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> productList1 =productService.getAllProducts();
        assertEquals(productList1,productList);
        verify(productRepository,times(1)).save(product1);
        verify(productRepository,times(1)).findAll();
    }

    @Test
    public void givenIdThenShouldReturnProductOfThatId() {
        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product1));
        assertThat(productService.getProductById(product1.getId())).isEqualTo(product1);
    }
}
