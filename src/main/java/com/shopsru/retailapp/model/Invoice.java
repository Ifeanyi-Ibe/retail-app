package com.shopsru.retailapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Invoice {
    private double bill;
    private double discount;
    private double total;
}
