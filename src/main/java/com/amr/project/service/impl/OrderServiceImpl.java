package com.amr.project.service.impl;

import com.amr.project.dao.impl.OrderDaoImpl;
import com.amr.project.model.entity.Mail;
import com.amr.project.model.entity.Order;
import com.amr.project.service.email.EmailSenderService;
import com.amr.project.util.TrackedEmailOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl extends ReadWriteServiceImpl<Order, Long> {

    private final OrderDaoImpl orderDaol;

    private final EmailSenderService emailSenderService;

    private final TrackedEmailOrder trackedEmailOrder;

    protected OrderServiceImpl(OrderDaoImpl dao, OrderDaoImpl orderDaol, EmailSenderService emailSenderService, TrackedEmailOrder trackedEmailOrder) {
        super(dao);
        this.orderDaol = orderDaol;
        this.emailSenderService = emailSenderService;
        this.trackedEmailOrder = trackedEmailOrder;
    }

    @Override
    @Transactional
    public void persist(Order order) {
        emailSenderService.sendSimpleEmail(trackedEmailOrder.trackedEmailOrderPersist(order));
        orderDaol.persist(order);
    }

    @Override
    @Transactional
    public void update(Order order) {
        Mail mail = trackedEmailOrder.trackedEmailOrderUpdate(order);
        if (mail != null)
            emailSenderService.sendSimpleEmail(mail);
        orderDaol.update(order);
    }

    @Override
    @Transactional
    public void delete(Order order) {
        emailSenderService.sendSimpleEmail(trackedEmailOrder.trackedEmailOrderDelete(order));
        orderDaol.delete(order);
    }
}
