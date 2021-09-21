package com.shopsru.retailapp.serviceImpl;

import com.shopsru.retailapp.dtos.ProductUpdateDto;
import com.shopsru.retailapp.model.Product;
import com.shopsru.retailapp.repositories.ProductRepository;
import com.shopsru.retailapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if(products.size() < 1) return null;
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        Product response = productRepository.findProductByName(product.getName());
        if(response != null) return null;
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public Product updateProduct(Long id, ProductUpdateDto productUpdateDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(!optionalProduct.isPresent()) return null;

        Product product = optionalProduct.get();

        product.setCategory(productUpdateDto.getCategory());
        product.setPrice(productUpdateDto.getPrice());
        return product;
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            productRepository.deleteById(id);
            return product.get();
        }
        return null;
    }
}
