package com.resttechsolutions.contactapi4.resource.dto.rerquest;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Wellington Adames on 11/09/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneRequestDto {
  private static final long serialVersionUID = 1L;
  private String name;
  private String phoneNumber;
  @JsonBackReference
  private Long customerId;
}
