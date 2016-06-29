/**
 * Package which contains all repositories
 */
package com.imie.component.repository;

import com.imie.component.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>Product Repository Class</b>
 *
 * <p> Product DAO </p>
 *
 * @author kevin boussard
 * @version 1.0
 */
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
