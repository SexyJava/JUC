package com.lyd.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("here");
        return 1024;
    }
}
/**
 * @Author Liuyunda
 * @Date 2021/4/22 22:16
 * @Email man021436@163.com
 * @Description: 第三种获得线程的方式
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask future = new FutureTask<>(new MyThread());
        // Object o = future.get();
        // System.out.println(o);
        new Thread(future,"A").start();
        System.out.println(future.get());
    }
}
