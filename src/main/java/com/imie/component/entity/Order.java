/**
 * Package which contains all entities
 */
package com.imie.component.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <b>Order Class</b>
 *
 * @see Entity
 * @see Table
 * @see Column
 *
 * @author kevin boussard
 * @version 1.0
 */
@Entity
@Table(name="component_order")
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder={"id", "ref", "dateGreated","customer","deliveries"})
public class Order implements Serializable {

    /**
     * Order ID
     *
     * <p>Unique ID - Auto-Generated value.</p>
     *
     * @see Order#getId()
     */
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private int id;

    /**
     * Order Reference
     * @see Order#getRef()
     */
    @Column(name="ref")
    @XmlElement
    private String ref;

    /**
     * Order Date Greated
     * @see Order#setDateGreated(Date)
     * @see Order#getDateGreated()
     */
    @Column(name="dateGreated", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:SSZ")
    @XmlElement
    private Date dateGreated;

    /**
     * Order Customer
     * @see Order#setCustomer(Customer)
     * @see Order#getCustomer()
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @XmlElement
    private Customer customer;

    /**
     * Order Deliveries
     * @see Order#setDeliveries(List)
     * @see Order#getDeliveries()
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "order")
    @XmlElement
    private List<Delivery> deliveries;

    /**
     * Order Constructor
     */
    public Order() { }

    /**
     * Order Constructor
     *
     * @param dateGreated
     *             Order Date Greated
     * @param customer
     *             Order Customer
     * @param deliveries
     *             Order Deliveries
     */
    public Order(Date dateGreated, Customer customer, List<Delivery> deliveries) {
        this.dateGreated = dateGreated;
        this.customer = customer;
        this.deliveries = deliveries;
    }

    /**
     * Get the Order Id
     * @return int - Order Id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the Order Reference
     * @return String - Order Reference
     */
    public String getRef() {
        return ref;
    }

    /**
     * Get the Order Date Greated
     * @return Date - Order Date Greated
     */
    public Date getDateGreated() {
        return dateGreated;
    }

    /**
     * Set a Date Greated
     * @param dateGreated - Date - Date Greated
     */
    public void setDateGreated(Date dateGreated) {
        this.dateGreated = dateGreated;
    }

    /**
     * Get the Order Customer
     * @return Customer - Order Customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set a Customer
     * @param customer - Customer - Order Customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Get the Order Deliveries
     * @return Delivery - Order Deliveries
     */
    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    /**
     * Set Deliveries
     * @param deliveries - List - Order Deliveries
     */
    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    /**
     * Function to enable to create an unique ref for a Order after create it.
     */
    @PostPersist
    public void PostPersist() {
        this.ref = "ref-ord-"+ this.id;
    }

    /**
     * Override toString Function.
     * @return name - String - Order Reference
     */
    @Override
    public String toString() {
        return this.ref;
    }
}
