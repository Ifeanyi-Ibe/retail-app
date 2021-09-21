package com.shopsru.retailapp.utilities;

import com.shopsru.retailapp.dtos.Item;
import com.shopsru.retailapp.model.Customer;
import com.shopsru.retailapp.service.CustomerService;
import com.shopsru.retailapp.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class DiscountCalculator {
    @Autowired
    private DiscountService discountService;
    @Autowired
    private CustomerService customerService;

    private double getItemDiscount(Item item, Long userId) {
        Customer customer = customerService.getCustomerById(userId);

        LocalDate currentDate = LocalDate.now();
        LocalDate dateAdded = customer.getDateAdded();

        Period age = Period.between(dateAdded, currentDate);
        int years = age.getYears();

        double amount = item.getTotal();
        double totalDiscount = 0;

        // Percentage discount apply only if item is not a grocery
        if(!item.getProduct().getCategory().equalsIgnoreCase("grocery")) {
            if(customer.isAffiliate()){
                totalDiscount += getPercentDiscount("affiliate", amount);
            }else if(customer.isEmployee()){
                totalDiscount += getPercentDiscount("employee", amount);
            }else if(years >= 2){
                totalDiscount += getPercentDiscount("oldCustomer", amount);
            }
        }
        return totalDiscount;
    }

    public double getPerHundredDiscount(double amount) {
        if(amount < 100) {
            return 0;
        }
        return 5 * (amount / 100);
    }

    public double getBillDiscount(List<Item> bill, Long userId) {
        double grandTotal = 0;
        // Get percent discount on items
        for(Item item : bill) {
            double discount = this.getItemDiscount(item, userId);
            grandTotal += discount;
        }
        // Get per hundred discount on total bill
        double totalBill = getTotalBill(bill);
        double perHundredDiscount = getPerHundredDiscount(totalBill);
        grandTotal += perHundredDiscount;

        return grandTotal;
    }

    public double getTotalBill(List<Item> bill) {
        double totalBill = 0;
        for(Item item : bill) {
            totalBill += item.getTotal();
        }
        return totalBill;
    }

    private double getPercentDiscount(String type, double amount) {
        double rate = discountService.getDiscountByType(type).getRate();
        return rate * amount;
    }
}
