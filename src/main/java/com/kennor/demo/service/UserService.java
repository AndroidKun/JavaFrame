package com.kennor.demo.service;

import com.kennor.demo.dao.UserDao;
import com.kennor.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserService extends BaseService<User>{
    @Autowired
    private UserDao userDao;
    @Override
    public void initDao() {
        baseDao = userDao;
    }
}
