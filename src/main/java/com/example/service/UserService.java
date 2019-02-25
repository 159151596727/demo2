package com.example.service;

import com.example.entity.Auuser;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 用户登录
     * @param loginCode
     * @param password
     * @return
     */
    public Auuser userLogin(String loginCode, String password){
        return userMapper.userLogin(loginCode,password);
    }

    /**
     * 验证用户名
     * @param name
     * @return
     */
    public boolean validataName(String name){
        return  userMapper.validataName(name) > 0;
    }

    /**
     * 验证手机号是否存在
     * @param mobile
     * @return
     */
    public boolean validataMobile(String mobile){
        return  userMapper.validataMobile(mobile) > 0;
    }

    /**
     * 添加用户
     * @param auuser
     * @return
     */
    public boolean insertUser(Auuser auuser){
        return userMapper.insertUser(auuser) > 0;
    }

    /**
     * 修改用户上次登录时间
     * @param loginCode
     * @return
     */
    public boolean modifyLastLogin(String loginCode){
        return  userMapper.modifyLastLogin(loginCode) > 0;
    }
}
