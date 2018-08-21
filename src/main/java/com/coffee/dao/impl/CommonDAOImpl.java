package com.coffee.dao.impl;

import com.coffee.dao.CommonDAO;
import com.coffee.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import java.util.List;

public class CommonDAOImpl implements CommonDAO {
    private static final String SELECT_PRICE_BY_SORT_NAME = "SELECT price FROM sort WHERE name='";
    private static final String SELECT_SORT_NAMES = "SELECT name FROM sort";
    private static final String QUOTE = "'";

    public double findPriceBySortName(String sortName) {
        double price;

        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        price = (Double) session.createNativeQuery("SELECT price FROM sort WHERE name='" + sortName + "'").list().get(0);


        return price;
    }

    public List<String> findAllSortNames() {
        List<String> sortNames;

        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        sortNames = session.createNativeQuery("SELECT name FROM sort").list();

        return sortNames;
    }

}
