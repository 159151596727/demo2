package com.example.demo;

import com.example.util.ThreadPoolUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create by 李印锋 on 2019/2/27 23:22
 */
public class test {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = ThreadPoolUtil.getInstance().addThread();
        executor.execute(()-> System.out.println("ss"));
        //executor.shutdown();
    }

}
