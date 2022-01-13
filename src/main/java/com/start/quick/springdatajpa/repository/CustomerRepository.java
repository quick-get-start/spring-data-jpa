package com.start.quick.springdatajpa.repository;

import com.start.quick.springdatajpa.entity.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * 客户信息仓库
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
