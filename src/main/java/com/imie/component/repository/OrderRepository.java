/**
 * Package which contains all repositories
 */
package com.imie.component.repository;

import com.imie.component.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <b>Order Repository Class</b>
 *
 * <p> Order DAO </p>
 *
 * @author kevin boussard
 * @version 1.0
 */
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findById(int id);
}
