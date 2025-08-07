package com.example.assess.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private String doorNo;
    private String streetName;
    private String city;
    private String pinCode;
    private int studentId;
}
