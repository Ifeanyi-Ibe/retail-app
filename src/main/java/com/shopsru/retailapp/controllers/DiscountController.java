package com.shopsru.retailapp.controllers;

import com.shopsru.retailapp.Response.ResponseDto;
import com.shopsru.retailapp.dtos.DiscountPercentResponse;
import com.shopsru.retailapp.dtos.DiscountUpdateDto;
import com.shopsru.retailapp.model.Discount;
import com.shopsru.retailapp.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/discount")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping
    public ResponseEntity<?> getAllDiscountTypes() {
        List<Discount> discountList = discountService.getAllDiscountTypes();
        if(discountList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "No discount types found."));
        }
        return ResponseEntity.ok(discountList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiscountById(@PathVariable("id") Long id) {
        Discount discount = discountService.getDiscountById(id);
        if(discount != null)
            return ResponseEntity.ok(discount);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "No discount type found for id: " + id));

        //return discountService.getDiscountById(id);
    }

    @GetMapping("/type")
    public ResponseEntity<?> getDiscountByType(@RequestParam("type") String type) {
        Discount discount = discountService.getDiscountByType(type);
        if(discount != null) {
            DiscountPercentResponse response = new DiscountPercentResponse();
            response.setPercentageDiscount(discount.getRate());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "Discount type does not exist"));
    }

    @PostMapping
    public ResponseEntity<?> createDiscount(@RequestBody Discount discount){
        Discount response = discountService.createDiscount(discount);
        if(response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Discount type already exists."));
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editDiscount(@PathVariable("id") Long id, @RequestBody DiscountUpdateDto discountUpdateDto) {
        Discount discount = discountService.updateDiscount(id, discountUpdateDto);
        if(discount == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "Discount type does not exist"));
        }
        return ResponseEntity.ok(discount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiscount(@PathVariable("id") Long id) {
        Discount discount = discountService.deleteDiscount(id);
        if(discount != null) return ResponseEntity.ok("Successfully removed discount type");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Discount type does not exist. Nothing to delete."));
    }
}
