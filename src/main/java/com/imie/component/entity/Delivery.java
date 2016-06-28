package com.imie.component.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kevin on 23/06/16.
 */
@Entity
@Table(name="component_delivery")
@XmlRootElement(name = "delivery")
@XmlAccessorType(XmlAccessType.NONE)
public class Delivery {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private int id;

    @Column(name="ref")
    @XmlElement
    private String ref;

    @Column(name="dateDelivery", nullable = false)
    @XmlElement
    private Date dateDelivery;

    @ManyToOne
    @JsonIgnore
    private Order order;

    @ManyToOne
    @XmlElement
    private Invoice invoice;


    public Delivery() { }

    public Delivery(String ref, Date dateDelivery, Order order, Invoice invoice) {
        this.dateDelivery = dateDelivery;
        this.order = order;
        this.invoice = invoice;
    }


    public int getId() {
        return id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Date getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Date dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @PostPersist
    public void PostPersist() {
        this.ref = "ref-del-"+ this.id;
    }

    @Override
    public String toString() {
        return this.ref;
    }
}
