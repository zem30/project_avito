package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.entity.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDaoImpl extends ReadWriteDaoImp<Shop,Long> implements ShopDao {

}
