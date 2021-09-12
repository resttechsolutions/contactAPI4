package com.resttechsolutions.contactapi4.resource.dto.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    @JsonManagedReference
    private List<PhoneResponseDTO> phones;
    @JsonManagedReference
    private List<AddressResponseDTO> addresses;
}
