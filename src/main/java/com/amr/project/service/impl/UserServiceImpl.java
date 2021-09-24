package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ReadWriteServiceImpl<User,Long>  {

    protected UserServiceImpl(ReadWriteDao<User, Long> dao) {
        super(dao);
    }
}
