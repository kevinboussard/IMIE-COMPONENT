package com.imie.component.repository;

import com.imie.component.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kevin on 23/06/16.
 */
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
