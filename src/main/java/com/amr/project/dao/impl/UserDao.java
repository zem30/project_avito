package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ReadWriteDaoImp;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends ReadWriteDaoImp<User,Long> {
}
