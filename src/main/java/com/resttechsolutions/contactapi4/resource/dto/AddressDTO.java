package com.resttechsolutions.contactapi4.resource.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String addressName;
    @JsonBackReference
    private ContactDTO contactDTO;
}
