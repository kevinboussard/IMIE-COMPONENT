/**
 * Package which contains all repositories
 */
package com.imie.component.repository;

import com.imie.component.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>Customer Repository Class</b>
 *
 * <p> Customer DAO </p>
 *
 * @author kevin boussard
 * @version 1.0
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
