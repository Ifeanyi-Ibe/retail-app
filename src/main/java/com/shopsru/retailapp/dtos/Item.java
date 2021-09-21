package com.shopsru.retailapp.dtos;

import com.shopsru.retailapp.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Item {
    private Product product;
    private int quantity;
    private double total;
}
