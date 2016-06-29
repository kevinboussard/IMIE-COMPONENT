/**
 * Package which contains all entities
 */
package com.imie.component.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * <b>Invoice Class</b>
 *
 * @see Entity
 * @see Table
 * @see Column
 *
 * @author kevin boussard
 * @version 1.0
 */
@Entity
@Table(name="component_invoice")
@XmlRootElement(name = "invoice")
@XmlAccessorType(XmlAccessType.NONE)
public class Invoice {

    /**
     * Invoice ID
     *
     * <p>Unique ID - Auto-Generated value.</p>
     *
     * @see Invoice#getId()
     */
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private int id;

    /**
     * Invoice Reference
     * @see Invoice#setRef(String)
     * @see Invoice#getRef()
     */
    @Column(name="ref")
    @XmlElement
    private String ref;

    /**
     * Invoice Date Invoice
     * @see Invoice#setDateInvoice(Date)
     * @see Invoice#getDateInvoice()
     */
    @Column(name="dateInvoice", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:SSZ")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:SSZ")
    @XmlElement
    private Date dateInvoice;

    /**
     * Invoice Deliveries
     * @see Invoice#setDeliveries(List)
     * @see Invoice#getDeliveries()
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice")
    @JsonIgnore
    private List<Delivery> deliveries;

    /**
     * Invoice Constructor
     */
    public Invoice() { }

    /**
     * Invoice Constructor
     * @param dateInvoice
     *             Invoice Date
     * @param deliveries
     *             Invoice Deliveries
     */
    public Invoice(Date dateInvoice, List<Delivery> deliveries) {
        this.dateInvoice = dateInvoice;
        this.deliveries = deliveries;
    }

    /**
     * Get the Invoice Id
     * @return int - Invoice Id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the Invoice Reference
     * @return String - Invoice Reference
     */
    public String getRef() {
        return ref;
    }

    /**
     * Set a Reference
     * @param ref - String - Invoice Reference
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * Get the Date Invoice
     * @return Date - Date Invoice
     */
    public Date getDateInvoice() {
        return dateInvoice;
    }

    /**
     * Set a Date Invoice
     * @param dateInvoice - Date - Date Invoice
     */
    public void setDateInvoice(Date dateInvoice) {
        this.dateInvoice = dateInvoice;
    }

    /**
     * Get Deliveries
     * @return - List - Invoice Deliveries
     */
    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    /**
     * Set Deliveries
     * @param deliveries - List - Invoice Deliveries
     */
    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    /**
     * Function to enable to create an unique ref for a Invoice after create it.
     */
    @PostPersist
    public void PostPersist() {
        this.ref = "ref-inv-"+ this.id;
    }

    /**
     * Override toString Function.
     * @return name - String - Invoice Reference
     */
    @Override
    public String toString() {
        return this.ref;
    }
}
