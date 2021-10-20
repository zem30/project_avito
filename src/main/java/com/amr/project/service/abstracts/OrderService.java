package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.entity.Order;
import com.amr.project.model.entity.User;

import java.util.List;

public interface OrderService extends ReadWriteService<Order, Long> {

     void changeStatusToPaid(long order_id);
     void changeStatusToSent(long order_id);
     void changeStatusToDelivered(long order_id);
     void changeStatusToCompleted(long order_id);

    void updateAddressAndUserInfo(Long id, OrderDto orderDto);

    void deleteItemInOrder(Long orderId, Long itemId);
    Order collectOrderByUserAndItems(List<ItemDto> items, User userOp);
}
