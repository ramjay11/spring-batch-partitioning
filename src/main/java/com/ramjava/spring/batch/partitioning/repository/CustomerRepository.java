package com.ramjava.spring.batch.partitioning.repository;

import com.ramjava.spring.batch.partitioning.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
