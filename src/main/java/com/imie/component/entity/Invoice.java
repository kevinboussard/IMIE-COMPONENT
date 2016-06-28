package com.imie.component.entity;

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
import java.util.List;

/**
 * Created by kevin on 23/06/16.
 */
@Entity
@Table(name="component_invoice")
@XmlRootElement(name = "invoice")
@XmlAccessorType(XmlAccessType.NONE)
public class Invoice {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private int id;

    @Column(name="ref")
    @XmlElement
    private String ref;

    @Column(name="dateInvoice", nullable = false)
    @XmlElement
    private Date dateInvoice;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice")
    @JsonIgnore
    private List<Delivery> deliveries;


    public Invoice() { }

    public Invoice(Date dateInvoice, List<Delivery> deliveries) {
        this.dateInvoice = dateInvoice;
        this.deliveries = deliveries;
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

    public Date getDateInvoice() {
        return dateInvoice;
    }

    public void setDateInvoice(Date dateInvoice) {
        this.dateInvoice = dateInvoice;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    @PostPersist
    public void PostPersist() {
        this.ref = "ref-inv-"+ this.id;
    }

    @Override
    public String toString() {
        return this.ref;
    }
}
