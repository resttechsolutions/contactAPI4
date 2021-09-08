package com.resttechsolutions.contactapi4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "contact")
@AllArgsConstructor
@NoArgsConstructor
public class Contact extends AbstractPersistable<Long> {

    private String name;
    private String email;
    @OneToMany(mappedBy = "contact", orphanRemoval = true)
    private List<Phone> phones;
    @OneToMany(mappedBy = "contact", orphanRemoval = true)
    private List<Address> addresses;
}
