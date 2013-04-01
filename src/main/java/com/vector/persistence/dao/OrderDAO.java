package com.vector.persistence.dao;

import com.vector.entity.dictionary.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO extends AbstractHibernateDAO<Order> {

    public OrderDAO() {
        super(Order.class);
    }
}
