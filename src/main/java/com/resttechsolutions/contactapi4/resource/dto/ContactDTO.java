package com.resttechsolutions.contactapi4.resource.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.resttechsolutions.contactapi4.entity.Address;
import com.resttechsolutions.contactapi4.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    @JsonManagedReference
    private List<PhoneDTO> phones;
    @JsonManagedReference
    private List<AddressDTO> addresses;
}
