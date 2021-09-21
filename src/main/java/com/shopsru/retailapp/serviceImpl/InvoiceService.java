package com.shopsru.retailapp.serviceImpl;

import com.shopsru.retailapp.dtos.BillRequest;
import com.shopsru.retailapp.dtos.Item;
import com.shopsru.retailapp.model.Invoice;
import com.shopsru.retailapp.utilities.DiscountCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    DiscountCalculator discountCalculator;

    public Invoice getTotalInvoice(BillRequest request) {
        Invoice invoice = new Invoice();

        Long userId = request.getUserId();
        List<Item> bill = request.getBill();

        double totalDiscount = discountCalculator.getBillDiscount(bill, userId);
        double totalAmount = discountCalculator.getTotalBill(bill);
        double totalInvoiceAmount = totalAmount - totalDiscount;

        invoice.setBill(totalAmount);
        invoice.setDiscount(totalDiscount);
        invoice.setTotal(totalInvoiceAmount);

        return invoice;
    }
}
