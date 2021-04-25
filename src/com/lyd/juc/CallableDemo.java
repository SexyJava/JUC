package com.lyd.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("here");
        try { TimeUnit.SECONDS.sleep(4); }catch (InterruptedException e) { e.printStackTrace(); }
        return 1024;
    }
}
/**
 * @Author Liuyunda
 * @Date 2021/4/22 22:16
 * @Email man021436@163.com
 * @Description: 第三种获得线程的方式
 *      get方法一般放在最后一行
 *      同一个Future对象启动多个线程，call方法只会执行一次，一个线程执行完成，结果会被复用
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask future = new FutureTask<>(new MyThread());
        // Object o = future.get();
        // System.out.println(o);
        new Thread(future,"A").start();
        new Thread(future,"B").start();
        System.out.println(Thread.currentThread().getName()+"----计算完成");
        System.out.println(future.get());
    }
}
