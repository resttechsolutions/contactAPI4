package com.resttechsolutions.contactapi4.repository;

import com.resttechsolutions.contactapi4.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
