/**
 * Package which contains all entities
 */
package com.imie.component.entity;

import javax.persistence.*;
import java.util.List;

/**
 * <b>Product Class</b>
 *
 * @see Entity
 * @see Table
 * @see Column
 *
 * @author kevin boussard
 * @version 1.0
 */
@Entity
@Table(name="component_product")
public class Product
{

    /**
     * Product ID
     *
     * <p>Unique ID - Auto-Generated value.</p>
     *
     * @see Product#getId()
     */
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Product Name
     * @see Product#setName(String)
     * @see Product#getName()
     */
    @Column(name="name", nullable = false)
    private String name;

    /**
     * Product Description
     * @see Product#setDescription(String)
     * @see Product#getDescription()
     */
    @Column(name="description")
    private String description;

    /**
     * Product Price
     * @see Product#setPrice(Float)
     * @see Product#getPrice()
     */
    @Column(name="price", nullable = false)
    private Float price;

    /**
     * Product OrderDetails
     * @see Product#setOrderDetails(List)
     * @see Product#getOrderDetails()
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private List<OrderDetail> orderDetails;

    /**
     * Product Constructor
     */
    public Product() { }

    /**
     * Product Constructor
     *
     * @param name
     *             Product Name
     * @param description
     *             Product Description
     * @param price
     *             Product Price
     * @param orderDetails
     *             Product OrderDetails
     */
    public Product(String name, String description, Float price, List<OrderDetail> orderDetails) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.orderDetails = orderDetails;
    }

    /**
     * Product Constructor
     *
     * @param name
     *             Product Name
     * @param price
     *             Product Price
     * @param orderDetails
     *             Product OrderDetails
     */
    public Product(String name, Float price, List<OrderDetail> orderDetails) {
        this.name = name;
        this.price = price;
        this.orderDetails = orderDetails;
    }

    /**
     * Get the Product Id
     * @return int - Product Id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the Product Name
     * @return String - Product String
     */
    public String getName() {
        return name;
    }

    /**
     * Set a Name
     * @param name - String - Product Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the Product Description
     * @return String - Product Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set a Description
     * @param description - String - Product Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the Product Price
     * @return Float - Product Price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * Set a Price
     * @param price - Float - Product Price
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * Get the Product OrderDetails
     * @return List - Product OrderDetails
     */
    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    /**
     * Set OrderDetails
     * @param orderDetails - List - Product OrderDetails
     */
    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    /**
     * Override toString Function.
     * @return name - String - Product Name
     */
    @Override
    public String toString() {
        return this.name;
    }
}
