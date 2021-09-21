package com.shopsru.retailapp.service;

import com.shopsru.retailapp.dtos.DiscountUpdateDto;
import com.shopsru.retailapp.model.Discount;

import java.util.List;

public interface DiscountService {
    List<Discount> getAllDiscountTypes();
    Discount createDiscount(Discount discount);
    Discount getDiscountById(Long id);
    Discount getDiscountByType(String type);
    Discount updateDiscount(Long id, DiscountUpdateDto discount);
    Discount deleteDiscount(Long id);
}
