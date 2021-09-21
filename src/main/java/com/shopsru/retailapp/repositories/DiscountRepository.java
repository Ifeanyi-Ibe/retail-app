package com.shopsru.retailapp.repositories;

import com.shopsru.retailapp.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Discount getDiscountByType(String type);
}
