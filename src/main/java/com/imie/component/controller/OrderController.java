/**
 * Package which contains all controllers
 */
package com.imie.component.controller;

import com.imie.component.entity.Order;
import com.imie.component.repository.OrderRepository;
import com.imie.component.tool.EntityList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <b>RestController Order class</b>
 *
 * @author kevin boussard
 * @version 1.0
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {


    @Autowired
    private OrderRepository orderRepository;

    /**
     *<b>Function to enable to retrieve all orders.</b>
     *
     * <p>It's a Web-Service is accessible via 'localhost:8080/api/order/get/all'.</p>
     * <p>Method : GET</p>
     *
     * <p>
     *     Return two formats : <br>
     *      XML - By default - Add to the request header : 'Accept':'application/xml' <br>
     *      JSON - Add to the request header : 'Accept':'application/json'
     * </p>
     *
     * @return List - All Orders
     */
    @RequestMapping(path = "/get/all", produces = {"application/json","application/xml"}, method = RequestMethod.GET)
    public @ResponseBody EntityList<Order> getOrders() {
        List<Order> orders =  orderRepository.findAll();

        EntityList<Order> listOfCustomers = new EntityList<Order>(orders);

        return listOfCustomers;

    }

    /**
     *<b>Function to enable to retrieve one order by its id.</b>
     *
     * <p>It's a Web-Service is accessible via 'localhost:8080/api/order/get/{orderId}'</p>
     * <p>Method : GET</p>
     *
     * <p>Example : localhost:8080/api/order/get/1</p>
     *
     * <p>
     *     Return two formats : <br>
     *      XML - By default - Add to the request header : 'Accept':'application/xml' <br>
     *      JSON - Add to the request header : 'Accept':'application/json'
     * </p>
     *
     * @param orderId - Order Id that will be retrieved
     * @return Order
     */
    @RequestMapping(path = "/get/{orderId}", produces = {"application/json", "application/xml"} ,method = RequestMethod.GET)
    public @ResponseBody Order getOrdersById(@PathVariable int orderId) {

        return orderRepository.findById(orderId);

    }

    /**
     * <b>Function to enable to create a new Order</b>
     *
     * <p>It's a Web-Service is accessible via 'localhost:8080/api/order/create'</p>
     * <p>Method : POST</p>
     *
     * <p>
     *     Request, two formats : <br>
     *      XML - Add to the request header : 'Content-Type':'application/xml' and the new Order. <br>
     *      JSON - Add to the request header : 'Content-Type':'application/json' and the new Order.
     * </p>
     *
     * <p>
     *     Example Request Body : <br>
     *
     *     JSON :
     *         { "dateGreated": "2016-06-27T22:00:00+0000" } <br>
     *
     *
     *     XML :
     *         &lt;order&gt; &lt;dateGreated&gt;2016-06-28T00:00:00+02:00 &lt;/dateGreated&gt; &lt;/order&gt;"
     *
     * </p>
     *
     *
     * @param order - Order that will be give to the request body
     * @return Order - The new Order
     */
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public @ResponseBody Order save(@RequestBody Order order) {

        orderRepository.save(order);
        return order;

    }
}
