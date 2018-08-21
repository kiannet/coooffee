package com.coffee.service;

import com.coffee.bean.OrderBean;
import com.coffee.dao.impl.OrderDAOImpl;


import java.util.List;

public class OrderService {
    OrderDAOImpl orderDAO = new OrderDAOImpl();

    public OrderService(){

    }

    public void saveOrder(OrderBean order) {
        orderDAO.save(order);
    }

    public void deleteOrder(OrderBean order) {
        orderDAO.delete(order);
    }

    public void updateOrder(OrderBean order) {
        orderDAO.update(order);
    }

    public List<OrderBean> findAllOrders() {
        return orderDAO.findAll();
    }
}
