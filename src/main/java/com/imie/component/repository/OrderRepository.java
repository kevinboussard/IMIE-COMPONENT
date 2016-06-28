package com.imie.component.repository;

import com.imie.component.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by kevin on 23/06/16.
 */
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findById(int id);
}
