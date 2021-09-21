package com.shopsru.retailapp.service;

import com.shopsru.retailapp.dtos.ProductUpdateDto;
import com.shopsru.retailapp.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product getProductById(Long id);
    Product updateProduct(Long id, ProductUpdateDto product);
    Product deleteProduct(Long id);
}
