package com.imie.component.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by kevin on 23/06/16.
 */
@Entity
@Table(name="component_order")
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder={"id", "ref", "dateGreated","customer","deliveries"})
public class Order implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private int id;

    @Column(name="ref")
    @XmlElement
    private String ref;

    @Column(name="dateGreated", nullable = false)
    @XmlElement
    private Date dateGreated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @XmlElement
    private Customer customer;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "order")
    @XmlElement
    private List<Delivery> deliveries;

    public Order() { }

    public Order(Date dateGreated, Customer customer, List<Delivery> deliveries) {
        this.dateGreated = dateGreated;
        this.customer = customer;
        this.deliveries = deliveries;
    }


    public int getId() {
        return id;
    }

    public String getRef() {
        return ref;
    }

    public Date getDateGreated() {
        return dateGreated;
    }

    public void setDateGreated(Date dateGreated) {
        this.dateGreated = dateGreated;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    @PostPersist
    public void PostPersist() {
        this.ref = "ref-ord-"+ this.id;
    }

    @Override
    public String toString() {
        return this.ref;
    }
}
