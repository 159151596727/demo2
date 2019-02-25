package com.example.serviceImpl;

import com.example.entity.Auuser;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper mapper;

    @Override
    public Auuser userLogin(String loginCode, String password) {
        return mapper.userLogin(loginCode,password);
    }

    @Override
    public boolean validataName(String name) {
        return mapper.validataName(name) > 0;
    }

    @Override
    public boolean validataMobile(String mobile) {
        return mapper.validataMobile(mobile) > 0;
    }

    @Override
    public boolean insertUser(Auuser auuser) {
        return mapper.insertUser(auuser) > 0;
    }

    @Override
    public boolean modifyLastLogin(String loginCode) {
        return mapper.modifyLastLogin(loginCode) > 0;
    }
}
