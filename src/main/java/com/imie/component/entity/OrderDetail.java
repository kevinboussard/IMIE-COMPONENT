package com.imie.component.entity;

import javax.persistence.*;

/**
 * Created by kevin on 23/06/16.
 */
@Entity
@Table(name="component_order_detail")
public class OrderDetail {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="qte", nullable = false)
    private float qte;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;


    public OrderDetail() { }

    public OrderDetail(float qte, Product product, Order order) {
        this.qte = qte;
        this.product = product;
        this.order = order;
    }


    public int getId() {
        return id;
    }

    public float getQte() {
        return qte;
    }

    public void setQte(float qte) {
        this.qte = qte;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return Integer.toString(this.id);
    }
}
