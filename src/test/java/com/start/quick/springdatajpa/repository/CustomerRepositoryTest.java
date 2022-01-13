package com.start.quick.springdatajpa.repository;

import com.start.quick.springdatajpa.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户信息仓库测试
 */
@DataJpaTest  // 自动配置JPA测试
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // 不启用内嵌数据库替代
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Transactional  // 开启事务
    @Rollback(value = false)  // 事务不回滚
    public void updateCustomerInTransaction() {
        Customer customer = new Customer();
        customer.setName("Hello Kitty!");
        customer.setPhone("17712345678");
        this.customerRepository.save(customer);

        Long id = customer.getId();
        this.customerRepository.findById(id).ifPresent(entity -> {
            entity.setName("Hello 冬泳怪鸽!");
            entity.setPhone("18888888888");
        });
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)  // 挂起/关闭事务
    public void test() {
        Customer customer = new Customer();
        customer.setName("Hello Kitty!");
        customer.setPhone("17712345678");
        this.customerRepository.save(customer);

        Long id = customer.getId();
        this.customerRepository.findById(id).ifPresent(entity -> {
            entity.setName("Hello 冬泳怪鸽!");
            entity.setPhone("18888888888");
        });
    }
}
