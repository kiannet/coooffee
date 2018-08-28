package com.coffee.dao.impl;

import com.coffee.dao.CommonDAO;
import com.coffee.entity.Sort;
import com.coffee.util.HibernateSessionFactoryUtil;

import java.util.List;

public class CommonDAOImpl implements CommonDAO {

    public double findPriceBySortName(String sortName) {

        double price;
        price = (Double)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("Select S.price from Sort S where S.name='" + sortName + "'").list().get(0);
        return price;


    }

    public List<Sort> findAllSortNames() {

        List<Sort> sorts = (List<Sort>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Sort").list();
        return sorts;

    }

}
