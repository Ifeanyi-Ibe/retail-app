package com.shopsru.retailapp.controllers;

import com.shopsru.retailapp.dtos.BillRequest;
import com.shopsru.retailapp.model.Invoice;
import com.shopsru.retailapp.serviceImpl.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public Invoice getInvoice(@RequestBody BillRequest request) {
       return invoiceService.getTotalInvoice(request);
    }

}
