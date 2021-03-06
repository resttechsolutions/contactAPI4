package com.resttechsolutions.contactapi4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
public class Address extends AbstractPersistable<Long> {

    private String name;
    private String addressName;
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;
}
