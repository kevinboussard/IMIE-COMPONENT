/**
 * Package which contains all entities
 */
package com.imie.component.entity;

import javax.persistence.*;

/**
 * <b>OrderDetail Class</b>
 *
 * @see Entity
 * @see Table
 * @see Column
 *
 * @author kevin boussard
 * @version 1.0
 */
@Entity
@Table(name="component_order_detail")
public class OrderDetail {

    /**
     * OrderDetail ID
     *
     * <p>Unique ID - Auto-Generated value.</p>
     *
     * @see OrderDetail#getId()
     */
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * OrderDetail Quantity
     * @see OrderDetail#setQte(float)
     * @see OrderDetail#getQte()
     */
    @Column(name="qte", nullable = false)
    private float qte;

    /**
     * OrderDetail Product
     * @see OrderDetail#setProduct(Product)
     * @see OrderDetail#getProduct()
     */
    @ManyToOne
    private Product product;

    /**
     * OrderDetail Order
     * @see OrderDetail#setOrder(Order)
     * @see OrderDetail#getOrder()
     */
    @ManyToOne
    private Order order;

    /**
     * OrderDetail Constructor
     */
    public OrderDetail() { }

    /**
     * OrderDetail Constructor
     *
     * @param qte
     *             OrderDetail Quantity
     * @param product
     *             OrderDetail Product
     * @param order
     *             OrderDetail Order
     */
    public OrderDetail(float qte, Product product, Order order) {
        this.qte = qte;
        this.product = product;
        this.order = order;
    }

    /**
     * Get the OrderDetail Id
     * @return int - OrderDetail Id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the OrderDetail Quantity
     * @return float - OrderDetail Quantity
     */
    public float getQte() {
        return qte;
    }

    /**
     * Set a Quantity
     * @param qte - float - OrderDetail Quantity
     */
    public void setQte(float qte) {
        this.qte = qte;
    }

    /**
     * Get the OrderDetail Product
     * @return Product - OrderDetail Product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Set a Product
     * @param product - Product - OrderDetail Product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Get the OrderDetail Order
     * @return Order - OrderDetail Order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Set an Order
     * @param order - Order - OrderDetail Order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Override toString Function.
     * @return name - String - OrderDetail Id
     */
    @Override
    public String toString() {
        return Integer.toString(this.id);
    }
}
