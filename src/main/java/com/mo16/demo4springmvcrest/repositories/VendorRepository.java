package com.mo16.demo4springmvcrest.repositories;

import com.mo16.demo4springmvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
