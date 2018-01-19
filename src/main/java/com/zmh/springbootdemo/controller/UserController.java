package com.zmh.springbootdemo.controller;

import com.zmh.springbootdemo.domain.User;
import com.zmh.springbootdemo.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRespository userRespository;

    @Autowired
    public UserController(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @PostMapping("/person/save")
    public User save(@RequestParam String name){
        User user = new User();
        user.setName(name);
        if (userRespository.save(user)) {
            System.out.printf("用户对象: %s 保存成功!\r\n",user);
        }
        return user;
    }
}
