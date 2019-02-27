package com.example.mapper;

import com.example.entity.Auuser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    @Select("SELECT * FROM auuser where loginCode=#{loginCode} and password=#{password}")
    Auuser userLogin(String loginCode, String password);
    @Select("SELECT COUNT(*) FROM auuser WHERE loginCode = #{name}")
    int validataName(String name);
    @Select("SELECT COUNT(*) FROM auuser WHERE mobile = #{mobile}")
    int validataMobile(String mobile);
    @Insert("INSERT INTO auuser VALUES(NULL,#{loginCode},#{password},#{loginCode},#{sex},#{mobile},NULL,NULL,NULL,NOW())")
    int insertUser(Auuser auuser);
    @Update("UPDATE auuser SET lastLogin =NOW() WHERE loginCode = #{loginCode}")
    int modifyLastLogin(String loginCode);
    @Select("SELECT * FROM auuser where id != #{id}")
    List<Auuser> getAuusers(Integer id);
}
