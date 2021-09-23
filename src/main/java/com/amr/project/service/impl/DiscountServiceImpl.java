package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.model.entity.Discount;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl extends ReadWriteServiceImpl<Discount,Long>{

    protected DiscountServiceImpl(ReadWriteDao<Discount, Long> dao) {
        super(dao);
    }
}
