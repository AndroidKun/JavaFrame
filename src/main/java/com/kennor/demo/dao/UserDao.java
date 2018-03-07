package com.kennor.demo.dao;

import com.kennor.demo.model.User;

import java.util.List;

public interface UserDao extends BaseDao<User>{

    List<User> findByName(String name);
    List<User> findByNameLike(String name);
    List<User> findByNameEqualsAndPhoneEquals(String name,String phone);
}
