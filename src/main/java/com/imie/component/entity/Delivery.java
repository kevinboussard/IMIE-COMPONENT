/**
 * Package which contains all entities
 */
package com.imie.component.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * <b>Delivery Class</b>
 *
 * @see Entity
 * @see Table
 * @see Column
 *
 * @author kevin boussard
 * @version 1.0
 */
@Entity
@Table(name="component_delivery")
@XmlRootElement(name = "delivery")
@XmlAccessorType(XmlAccessType.NONE)
public class Delivery {

    /**
     * Delivery ID
     *
     * <p>Unique ID - Auto-Generated value.</p>
     *
     * @see Delivery#getId()
     */
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private int id;

    /**
     * Delivery Reference
     * @see Delivery#setRef(String)
     * @see Delivery#getRef()
     */
    @Column(name="ref")
    @XmlElement
    private String ref;

    /**
     * Delivery Date Delivery
     * @see Delivery#setDateDelivery(Date)
     * @see Delivery#getDateDelivery()
     */
    @Column(name="dateDelivery", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:SSZ")
    @XmlElement
    private Date dateDelivery;

    /**
     * Delivery Order
     * @see Delivery#setOrder(Order)
     * @see Delivery#getOrder()
     */
    @ManyToOne
    @JsonIgnore
    private Order order;

    /**
     * Delivery invoice
     * @see Delivery#setInvoice(Invoice)
     * @see Delivery#getInvoice()
     */
    @ManyToOne
    @XmlElement
    private Invoice invoice;

    /**
     * Delivery Constructor
     */
    public Delivery() { }

    /**
     * Delivery Constructor
     *
     * @param ref
     *             Delivery Reference
     * @param dateDelivery
     *             Delivery Date Delivery
     * @param order
     *             Delivery Order
     * @param invoice
     *             Delivery Invoice
     */
    public Delivery(String ref, Date dateDelivery, Order order, Invoice invoice) {
        this.dateDelivery = dateDelivery;
        this.order = order;
        this.invoice = invoice;
    }

    /**
     * Get the Delivery Id
     * @return int - Delivery Id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the Delivery Reference
     * @return String - Delivery Reference
     */
    public String getRef() {
        return ref;
    }

    /**
     * Set a Reference
     * @param ref - String - Delivery Reference
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * Get the Date Delivery
     * @return Date -  Date Delivery
     */
    public Date getDateDelivery() {
        return dateDelivery;
    }

    /**
     * Set a Date Delivery
     * @param dateDelivery - Date - Delivery Date Delivery
     */
    public void setDateDelivery(Date dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    /**
     * Get the Delivery Order
     * @return Order - Delivery Date Order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Set an Order
     * @param order - Order - Delivery Order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Get the Delivery Invoice
     * @return Invoice - Delivery Date Invoice
     */
    public Invoice getInvoice() {
        return invoice;
    }

    /**
     * Set an Invoice
     * @param invoice - Invoice - Delivery Invoice
     */
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    /**
     * Function to enable to create an unique ref for a Delivery after create it.
     */
    @PostPersist
    public void PostPersist() {
        this.ref = "ref-del-"+ this.id;
    }

    /**
     * Override toString Function.
     * @return name - String - Delivery Reference
     */
    @Override
    public String toString() {
        return this.ref;
    }
}
