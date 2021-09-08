package com.resttechsolutions.contactapi4.resource.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String phoneNumber;
    @JsonBackReference
    private ContactDTO contactDTO;
}
