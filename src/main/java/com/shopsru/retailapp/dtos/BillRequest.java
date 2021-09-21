package com.shopsru.retailapp.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BillRequest {
    private Long userId;
    private List<Item> bill;
}
