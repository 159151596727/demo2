package com.example.service;

import com.example.entity.Auuser;

public interface UserService {
    /**
     * 用户登录
     * @param loginCode
     * @param password
     * @return
     */
    Auuser userLogin(String loginCode, String password);

    /**
     * 验证用户名
     * @param name
     * @return
     */
    boolean validataName(String name);

    /**
     * 验证手机号是否存在
     * @param mobile
     * @return
     */
    boolean validataMobile(String mobile);

    /**
     * 添加用户
     * @param auuser
     * @return
     */
    boolean insertUser(Auuser auuser);

    /**
     * 修改用户上次登录时间
     * @param loginCode
     * @return
     */
    boolean modifyLastLogin(String loginCode);
}
