package com.example.mapper;

import com.example.entity.Chatrecord;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

/**
 * Created by 李印锋 on 2/27/0027 17:05
 */
@Component
public interface ChatrecordMapper {
    @Insert("INSERT INTO chatrecord VALUES(NULL,#{sender},#{sendMessage},#{receiver},NOW())")
    int insertMessage(Chatrecord Chatrecord);
}
