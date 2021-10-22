package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.UserPageOrderDao;
import com.amr.project.model.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPageOrderDaoImpl extends ReadWriteDaoImpl<Order, Long> implements UserPageOrderDao{


    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return null;
    }
}
