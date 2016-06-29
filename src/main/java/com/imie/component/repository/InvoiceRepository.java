/**
 * Package which contains all repositories
 */
package com.imie.component.repository;

import com.imie.component.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>Invoice Repository Class</b>
 *
 * <p> Invoice DAO </p>
 *
 * @author kevin boussard
 * @version 1.0
 */
public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {

}
