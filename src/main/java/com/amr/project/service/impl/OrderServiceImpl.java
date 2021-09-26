package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.model.entity.Order;
import com.amr.project.model.enums.Status;
import com.amr.project.service.abstracts.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl extends ReadWriteServiceImpl<Order, Long> implements OrderService {

    @Autowired
    public OrderServiceImpl(ReadWriteDao<Order, Long> dao) {
        super(dao);
    }

    @Override
    public void changeStatusToPaid(long order_id) {
        Order order = this.getByKey(order_id);
        order.setStatus(Status.PAID);
        this.update(order);
    }

    @Override
    public void changeStatusToSent(long order_id) {
        Order order = this.getByKey(order_id);
        order.setStatus(Status.SENT);
        this.update(order);
    }

    @Override
    public void changeStatusToDelivered(long order_id) {
        Order order = this.getByKey(order_id);
        order.setStatus(Status.DELIVERED);
        this.update(order);
    }

    @Override
    public void changeStatusToCompleted(long order_id) {
        Order order = this.getByKey(order_id);
        order.setStatus(Status.COMPLETE);
        this.update(order);
    }
}
