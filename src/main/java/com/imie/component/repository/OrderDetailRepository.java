/**
 * Package which contains all repositories
 */
package com.imie.component.repository;

import com.imie.component.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>OrderDetail Repository Class</b>
 *
 * <p> OrderDetail DAO </p>
 *
 * @author kevin boussard
 * @version 1.0
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
}
