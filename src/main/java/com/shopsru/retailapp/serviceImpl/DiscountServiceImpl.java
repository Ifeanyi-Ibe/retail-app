package com.shopsru.retailapp.serviceImpl;

import com.shopsru.retailapp.dtos.DiscountUpdateDto;
import com.shopsru.retailapp.model.Discount;
import com.shopsru.retailapp.repositories.CustomerRepository;
import com.shopsru.retailapp.repositories.DiscountRepository;
import com.shopsru.retailapp.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Discount> getAllDiscountTypes() {
        List<Discount> discountTypes = discountRepository.findAll();
        if(discountTypes.size() < 1) return null;
        return discountTypes;
    }

    @Override
    public Discount createDiscount(Discount discount) {
        Discount response = discountRepository.getDiscountByType(discount.getType());
        if(response == null) return discountRepository.save(discount);
        return null;
    }

    @Override
    public Discount getDiscountById(Long id) {
        Optional<Discount> discount = discountRepository.findById(id);
        return discount.orElse(null);
    }

    @Override
    public Discount getDiscountByType(String type) {

        return discountRepository.getDiscountByType(type);
    }

    @Override
    public Discount updateDiscount(Long id, DiscountUpdateDto discountUpdateDto) {
        Optional<Discount> optionalDiscount = discountRepository.findById(id);
        if(!optionalDiscount.isPresent()) {
            return null;
        }
        Discount discount = optionalDiscount.get();
        discount.setRate(discountUpdateDto.getRate());
        return discountRepository.save(discount);
    }

    @Override
    public Discount deleteDiscount(Long id) {
        Optional<Discount> discount = discountRepository.findById(id);
        if(discount.isPresent()) {
            discountRepository.deleteById(id);
            return discount.get();
        } else return null;
    }



}
