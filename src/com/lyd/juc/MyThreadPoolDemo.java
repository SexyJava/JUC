package com.lyd.juc;

import java.util.concurrent.*;

/**
 * @Author Liuyunda
 * @Date 2021/4/26 21:22
 * @Email man021436@163.com
 * @Description: 线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // ExecutorService threadPool = Executors.newCachedThreadPool();


        try{
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t get");
                });
                try { TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e) { e.printStackTrace(); }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
