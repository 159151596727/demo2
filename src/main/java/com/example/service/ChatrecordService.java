package com.example.service;

import com.example.entity.Chatrecord;
import com.example.mapper.ChatrecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 李印锋 on 2/27/0027 17:11
 */
@Service
public class ChatrecordService implements ChatrecordMapper {
    @Autowired
    private ChatrecordMapper chatrecordMapper;

    @Override
    public int insertMessage(Chatrecord Chatrecord) {
        return chatrecordMapper.insertMessage(Chatrecord);
    }
}
