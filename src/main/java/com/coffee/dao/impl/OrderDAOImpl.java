package com.coffee.dao.impl;

import com.coffee.bean.OrderBean;
import com.coffee.dao.OrderDAO;

import com.coffee.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    public void save(OrderBean order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(order);
        tx1.commit();
        session.close();
    }

    public void update(OrderBean order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(order);
        tx1.commit();
        session.close();
    }

    public void delete(OrderBean order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(order);
        tx1.commit();
        session.close();
    }

    public List<OrderBean> findAll() {
        List<OrderBean> orders = (List<OrderBean>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From OrderBean").list();
        return orders;
    }
}
