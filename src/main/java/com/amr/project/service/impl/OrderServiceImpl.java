package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.OrderDao;
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

    private final OrderDao orderDao;

    public OrderServiceImpl(ReadWriteDao<Order, Long> dao, OrderDao orderDao) {
        super(dao);
        this.orderDao = orderDao;
    }

    @Override
    public void changeStatusToPaid(long order_id) {
        Order order = this.getByKey(order_id);
        order.setStatus(Status.PAID);
        orderDao.update(order);
    }

    @Override
    public void changeStatusToSent(long order_id) {
        Order order = this.getByKey(order_id);
        order.setStatus(Status.SENT);
        orderDao.update(order);
    }

    @Override
    public void changeStatusToDelivered(long order_id) {
        Order order = this.getByKey(order_id);
        order.setStatus(Status.DELIVERED);
        orderDao.update(order);
    }

    @Override
    public void changeStatusToCompleted(long order_id) {
        Order order = this.getByKey(order_id);
        order.setStatus(Status.COMPLETE);
        orderDao.update(order);
    }
}
