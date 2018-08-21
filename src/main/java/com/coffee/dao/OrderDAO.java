package com.coffee.dao;

import com.coffee.bean.OrderBean;


import java.util.List;

public interface OrderDAO {
    void save(OrderBean order);
    void update(OrderBean order);
    void delete(OrderBean order);
    List<OrderBean> findAll();
}
