/**
 * Package which contains all repositories
 */
package com.imie.component.repository;

import com.imie.component.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>Delivery Repository Class</b>
 *
 * <p> Delivery DAO </p>
 *
 * @author kevin boussard
 * @version 1.0
 */
public interface DeliveryRepository extends JpaRepository<Delivery,Integer> {
}
