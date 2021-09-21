package com.shopsru.retailapp.repositories;

import com.shopsru.retailapp.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    private Product product;
    @BeforeEach
    public void setUp() {
        product = new Product(1L,"Milo", "grocery",2500);
    }
    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
        product = null;
    }

    @Test
    public void givenProductToAddShouldReturnAddedProduct(){
        Product product = new Product(1L,"Milo", "grocery",2500);
        Product savedProduct = productRepository.save(product);
        Product fetchedProduct = productRepository.findById(savedProduct.getId()).get();
        assertEquals("Milo", fetchedProduct.getName());
    }

    @Test
    public void givenGetAllProductShouldReturnListOfAllProducts(){
        Product product1 = new Product(1L,"Milo", "grocery",2500);
        Product product2 = new Product(2L,"Oven", "appliances",25300);
        productRepository.save(product1);
        productRepository.save(product2);
        List<Product> productList = (List<Product>) productRepository.findAll();
        assertEquals("Ariel", productList.get(1).getName());
    }

    @Test
    public void givenIdThenShouldReturnProductOfThatId() {
        Product product1 = new Product(1L,"Milo", "grocery",2500);
        Product product2 = productRepository.save(product1);
        Optional<Product> optional =  productRepository.findById(product2.getId());
        assertEquals(product2.getId(), optional.get().getId());
        assertEquals(product2.getName(), optional.get().getName());
    }

    @Test
    public void givenIdTODeleteThenShouldDeleteTheProduct() {
        Product product = new Product(5L,"Milo", "grocery",2500);
        productRepository.save(product);
        productRepository.delete(product);
        Optional<Product> optional = productRepository.findById(product.getId());
        assertEquals(Optional.empty(), optional);
    }

}
