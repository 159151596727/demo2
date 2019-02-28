package com.example.threadUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by 李印锋 on 2019年2月28日10点29分
 * 阻塞线程池 保证每一个线程都能执行完成
 */
public class CustomThreadPoolExecutor {


    private static ThreadPoolExecutor pool = null;

    /**
     * 线程池初始化方法
     *
     * corePoolSize 核心线程池大小----1
     * maximumPoolSize 最大线程池大小----3
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit
     * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(5)====5容量的阻塞队列
     * threadFactory 新建线程工厂----new CustomThreadFactory()====定制的线程工厂
     * rejectedExecutionHandler 当提交任务数超过maxmumPoolSize+workQueue之和时,
     *                          即当提交第41个任务时(前面线程都没有执行完,此测试方法中用sleep(100)),
     *                                任务会交给RejectedExecutionHandler来处理
     */
    public static ThreadPoolExecutor getCustomThreadPoolExecutor() {
        if (pool == null){
            pool = new ThreadPoolExecutor(
                    1,
                    3,
                    30,
                    TimeUnit.MINUTES,
                    new ArrayBlockingQueue<Runnable>(5),
                    new CustomThreadFactory(),
                    new CustomRejectedExecutionHandler());
        }
        return pool;
    }
}