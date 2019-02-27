package com.example.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create by 李印锋 on 2019/2/27 23:33
 */
public class ThreadPoolUtil {
    static ThreadPoolUtil instance = new ThreadPoolUtil();

    /**
     * 创建队列
     */
    BlockingQueue queue = new LinkedBlockingQueue(10);

    public static ThreadPoolUtil getInstance() {
        return instance;
    }

    /**
     * 添加一个线程任务
     * corePoolSize  线程池的基本大小
     * maximumPoolSizeSize 线程池最大数量
     * keepAliveTime 线程活动保持时间
     * unit 线程活动保持时间的单位
     * workQueue 任务队列
     * threadFactory 用于创建线程的工厂
     * @return
     */
    public ThreadPoolExecutor addThread(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10, 1,
                TimeUnit.SECONDS, queue,new ThreadPoolExecutor.AbortPolicy());
        return  executor;
    }

}
