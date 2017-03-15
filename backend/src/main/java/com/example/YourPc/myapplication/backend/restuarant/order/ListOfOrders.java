package com.example.YourPc.myapplication.backend.restuarant.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Saeed on 3/3/2017.
 */
public class ListOfOrders {
    public List<Order> orderList = new ArrayList<>();

    public ListOfOrders(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "ListOfOrders{" +
                "orderList=" + orderList +
                '}';
    }
}
