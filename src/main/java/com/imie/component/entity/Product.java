package com.imie.component.entity;

import javax.persistence.*;
import java.util.List;


/**
 * Created by kevin on 23/06/16.
 */
@Entity
@Table(name="component_product")
public class Product
{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price", nullable = false)
    private Float price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private List<OrderDetail> orderDetails;


    public Product() { }

    public Product(String name, String description, Float price, List<OrderDetail> orderDetails) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.orderDetails = orderDetails;
    }

    public Product(String name, Float price, List<OrderDetail> orderDetails) {
        this.name = name;
        this.price = price;
        this.orderDetails = orderDetails;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
