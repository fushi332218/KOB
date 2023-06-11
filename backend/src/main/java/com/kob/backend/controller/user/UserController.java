package com.kob.backend.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    //测试用api
    @GetMapping("/user/all/")
    public List<User> getAll() {
        return userMapper.selectList(null);
    }
    //测试用api
    @GetMapping("/user/{userid}/")
    public User getuser(@PathVariable int userid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.ge("id", 1).le("id", 2);
        queryWrapper.eq("id", userid);
        return userMapper.selectOne(queryWrapper);
        //return userMapper.selectById(userid);
    }
    //测试用api
    @GetMapping("/user/add/{userId}/{username}/{password}")
    public String addUser (@PathVariable int userId, @PathVariable String username, @PathVariable String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(userId, username, encodedPassword);
        userMapper.insert(user);
        return "Add User Successfully";
    }

    @GetMapping("/user/delete/{userId}/")
    public String deleteUser (@PathVariable int userId) {
        userMapper.deleteById(userId);
        return "Delete User Successfuly";
    }
}