package com.resttechsolutions.contactapi4.repository;

import com.resttechsolutions.contactapi4.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
