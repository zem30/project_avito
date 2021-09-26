package com.amr.project.service.impl;

import com.amr.project.dao.impl.OrderDaoImpl;
import com.amr.project.model.entity.Mail;
import com.amr.project.model.entity.Order;
import com.amr.project.model.enums.Status;
import com.amr.project.service.abstracts.OrderService;
import com.amr.project.service.email.EmailSenderService;
import com.amr.project.util.TrackedEmailOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl extends ReadWriteServiceImpl<Order, Long> implements OrderService {

    private final OrderDaoImpl orderDao;
    private final EmailSenderService emailSenderService;
    private final TrackedEmailOrder trackedEmailOrder;

    protected OrderServiceImpl(OrderDaoImpl dao, OrderDaoImpl orderDao,
                               EmailSenderService emailSenderService,
                               TrackedEmailOrder trackedEmailOrder) {
        super(dao);
        this.orderDao = orderDao;
        this.emailSenderService = emailSenderService;
        this.trackedEmailOrder = trackedEmailOrder;
    }

    @Override
    public void persist(Order order) {
        emailSenderService.sendSimpleEmail(trackedEmailOrder.trackedEmailOrderPersist(order));
        orderDao.persist(order);
    }

    @Override
    public void update(Order order) {
        Mail mail = trackedEmailOrder.trackedEmailOrderUpdate(order);
        if (mail != null)
            emailSenderService.sendSimpleEmail(mail);
        orderDao.update(order);
    }

    @Override
    public void delete(Order order) {
        emailSenderService.sendSimpleEmail(trackedEmailOrder.trackedEmailOrderDelete(order));
        orderDao.delete(order);
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
