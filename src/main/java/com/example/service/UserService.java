package com.example.service;

import com.example.entity.Auuser;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements  UserMapper{

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
    public int validataName(String name){
        return  userMapper.validataName(name);
    }

    /**
     * 验证手机号是否存在
     * @param mobile
     * @return
     */
    public int validataMobile(String mobile){
        return  userMapper.validataMobile(mobile);
    }

    /**
     * 添加用户
     * @param auuser
     * @return
     */
    public int insertUser(Auuser auuser){
        return userMapper.insertUser(auuser);
    }

    /**
     * 修改用户上次登录时间
     * @param loginCode
     * @return
     */
    public int modifyLastLogin(String loginCode){
        return  userMapper.modifyLastLogin(loginCode);
    }

    public List<Auuser> getAuusers(Integer id){
        return  userMapper.getAuusers(id);
    }
}
