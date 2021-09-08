package com.resttechsolutions.contactapi4.repository;

import com.resttechsolutions.contactapi4.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
