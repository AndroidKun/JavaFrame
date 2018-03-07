package com.kennor.demo.controller;

import com.kennor.demo.common.PageResult;
import com.kennor.demo.config.WxConfig;
import com.kennor.demo.model.User;
import com.kennor.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "index",method = RequestMethod.GET)
//    @GetMapping(value = "/index")
    public String index(){
        return "Hello,Spring Boot!"+wxConfig.getId();
    }

    @GetMapping(value = "userList")
    public List<User> getUser(){
        List<User> all = (List<User>) userService.findAll(null);
        return all;
    }

    @GetMapping(value = "userPage")
    public  PageResult<User> getUserPage(@RequestParam(name = "name",required = false) String name,@RequestParam(name = "phone",required = false) String phone
    ,@RequestParam(name = "page",defaultValue = "1") Integer page,@RequestParam(name="size",defaultValue = "10") Integer size){
       page--;
       User user = new User();
       user.setName(name);
       user.setPhone(phone);
       PageResult<User> byPage = userService.findByPage(user, page, size);
       return byPage;
    }

    @GetMapping(value = "userCount")
    public String getUserCount(@RequestParam(name = "phone") String phone){
        User user = new User();
        user.setPhone(phone);
        return  userService.count(null)+"";
    }

   @GetMapping(value = "userList/{name}")
    public List<User> getUser(@PathVariable(name = "name") String name){
        User user = new User();
        user.setName(name);
       List<User> all = (List<User>) userService.findAll(user);
        return all;
    }
/*
    @GetMapping(value = "findUsers")
    public List<User> getUser2(@RequestParam(name = "name") String name,@RequestParam(name = "phone") String phone){
        return userDao.findByNameEqualsAndPhoneEquals(name,phone);
    }*/
}
