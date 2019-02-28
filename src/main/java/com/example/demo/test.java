package com.example.demo;

import com.example.threadUtil.CustomThreadPoolExecutor;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create by 李印锋 on 2019/2/27 23:22
 */
public class test {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = CustomThreadPoolExecutor.getCustomThreadPoolExecutor();
        /*for(int i=1; i<100; i++) {
            System.out.println("提交第" + i + "个任务!");
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(">>>task is running=====");
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }*/

        executor.execute(() -> System.out.println("c"));
        // 2.销毁----此处不能销毁,因为任务没有提交执行完,如果销毁线程池,任务也就无法执行了
        executor.shutdownNow();
        ThreadPoolExecutor executor1 = CustomThreadPoolExecutor.getCustomThreadPoolExecutor();
        executor1.execute(() -> System.out.println("d"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
