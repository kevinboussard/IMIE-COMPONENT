package com.imie.component.repository;

import com.imie.component.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kevin on 23/06/16.
 */
public interface DeliveryRepository extends JpaRepository<Delivery,Integer> {
}
