package com.shopsru.retailapp.controllers;

import com.shopsru.retailapp.Response.ResponseDto;
import com.shopsru.retailapp.dtos.ProductUpdateDto;
import com.shopsru.retailapp.model.Product;
import com.shopsru.retailapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if(products == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "No product found."));
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        if(product != null)
            return ResponseEntity.ok(product);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "No product found for id: " + id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Product response = productService.createProduct(product);
        if(response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Product already exists."));
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody ProductUpdateDto productUpdateDto) {
        Product product = productService.updateProduct(id, productUpdateDto);

        if(product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "Product does not exist."));
        }
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        Product product = productService.deleteProduct(id);
        if(product == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Product does not exist. Nothing to delete."));
        return ResponseEntity.ok("Successfully deleted product.");
    }
}
