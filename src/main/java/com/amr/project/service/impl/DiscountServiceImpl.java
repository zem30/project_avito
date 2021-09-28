package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.model.entity.Discount;
import com.amr.project.service.abstracts.DiscountService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl extends ReadWriteServiceImpl<Discount, Long> implements DiscountService {

//    private final UserService userService;

    protected DiscountServiceImpl(ReadWriteDao<Discount, Long> dao
//            , UserService userService
    ) {
        super(dao);
//        this.userService = userService;
    }

    /*
    В теле Discount не Discount.id a User.id.
    По id достаём пользователя и sett-им его в Discount.
     */
//    @Override
//    public void persist(Discount obj) {
//        Discount discount = Discount.builder()
//                .fixedDiscount(obj.getFixedDiscount())
//                .percentage(obj.getPercentage())
//                .minOrder(obj.getMinOrder())
//                .shop(obj.getShop())
//                .user(userService.getByKey(obj.getId()))
//                .build();
//        super.persist(discount);
//
//    }
}
