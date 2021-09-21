package com.shopsru.retailapp.dtos;

import lombok.Getter;

@Getter
public class CustomerUpdateDto {
    private String name;
    private String email;
    private String phoneNumber;
    private boolean affiliate = false;
    private boolean employee = false;
}
