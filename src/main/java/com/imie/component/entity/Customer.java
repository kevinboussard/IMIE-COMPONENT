package com.imie.component.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * <b>Customer Class</b>
 *
 * @see Entity
 * @see Table
 * @see Column
 *
 * @author kevin boussard
 * @version 1.0
 */
@Entity
@Table(name="component_customer")
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.NONE)
public class Customer {

    /**
     * Customer ID
     *
     * <p>Unique ID - Auto-Generated value.</p>
     *
     * @see Customer#getId()
     */
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private int id;

    /**
     * Customer Reference
     * @see Customer#setRef(String)
     * @see Customer#getRef()
     */
    @Column(name="ref")
    @XmlElement
    private String ref;

    /**
     * Customer Name
     * @see Customer#setName(String)
     * @see Customer#getName()
     */
    @Column(name="name", nullable = false)
    @XmlElement
    private String name;

    /**
     * Customer Address
     * @see Customer#setAddress(String)
     * @see Customer#getAddress()
     */
    @Column(name="address", nullable = false)
    @XmlElement
    private String address;

    /**
     * Customer Zip
     * @see Customer#setZip(String)
     * @see Customer#getZip()
     */
    @Column(name="zip", nullable = false)
    @XmlElement
    private String zip;

    /**
     * Customer City
     * @see Customer#setCity(String)
     * @see Customer#getCity()
     */
    @Column(name="city", nullable = false)
    @XmlElement
    private String city;

    /**
     * Customer Phone
     * @see Customer#setPhone(String)
     * @see Customer#getPhone()
     */
    @Column(name="phone")
    @XmlElement
    private String phone;

    /**
     * Customer Email
     * @see Customer#setEmail(String)
     * @see Customer#getEmail()
     */
    @Column(name="email", nullable = false)
    @XmlElement
    private String email;

    /**
     * Customer Orders
     * @see Customer#setOrders(List)
     * @see Customer#getOrders()
     * @see Order
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    @JsonIgnore
    private List<Order> orders;

    /**
     * Customer Constructor
     */
    public Customer() { }

    /**
     * Customer Constructor
     *
     * @param name
     *             Customer Name
     * @param address
     *             Customer Address
     * @param zip
     *             Customer Zip
     * @param city
     *             Customer City
     * @param email
     *             Customer Email
     * @param orders
     *             Customer Orders
     * @param phone
     *             Customer Phone
     *
     *
     * @see Customer#name
     * @see Customer#address
     * @see Customer#zip
     * @see Customer#city
     * @see Customer#email
     * @see Customer#orders
     * @see Customer#phone
     */
    public Customer(String name, String address, String zip, String city, String email, List<Order> orders, String phone) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.email = email;
        this.orders = orders;
        this.phone = phone;
    }

    /**
     * Customer Constructor
     *
     * @param name
     *             Customer Name
     * @param address
     *             Customer Address
     * @param zip
     *             Customer Zip
     * @param city
     *             Customer City
     * @param email
     *             Customer Email
     * @param orders
     *             Customer Orders
     *
     * @see Customer#name
     * @see Customer#address
     * @see Customer#zip
     * @see Customer#city
     * @see Customer#email
     * @see Customer#orders
     */
    public Customer(String name, String address, String zip, String city, String email, List<Order> orders) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.email = email;
        this.orders = orders;
    }

    /**
     * @return int  - Customer Id
     */
    public int getId() {
        return id;
    }

    /**
     * @return String  - Customer Ref
     */
    public String getRef() {
        return ref;
    }

    /**
     * @param ref - String - Customer Reference
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * @return String  - Customer Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name - String - Customer Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String  - Customer Address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address - String - Customer Address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return String  - Customer Zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip - String - Customer Zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return String  - Customer City
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city - String - Customer City
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return String  - Customer Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email - String - Customer Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String  - Customer Phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone - String - Customer Phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return List  - Customer Orders
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * @param orders - List - Customer Orders
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    /**
     * Function to enable to create an unique ref for a Customer after create it.
     */
    @PostPersist
    public void PostPersist() {
        this.ref = "ref-cus-" + this.id;
    }

    /**
     * Override toString Function.
     * @return name - String - Customer Name
     */
    @Override
    public String toString() {
        return this.name;
    }
}
