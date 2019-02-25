package com.example.mapper;

import com.example.entity.Auuser;

//@Repository 启动类使用了@MapperScan就可以不写
public interface UserMapper {
    Auuser userLogin(String loginCode, String password);
    int validataName(String name);
    int validataMobile(String mobile);
    int insertUser(Auuser auuser);
    int modifyLastLogin(String loginCode);
}
