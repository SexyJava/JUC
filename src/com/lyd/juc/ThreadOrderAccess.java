package com.lyd.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Liuyunda
 * @Date 2021/4/20 21:29
 * @Email man021436@163.com
 * @Description: 多线程之间按顺序调用，实现A->B->C，三个线程启动要求如下：AA打印五次，BB打印10次，CC打印15次，来10轮
 * 标志位
 */
class ShareResource{
    /**
     * 标志位：1A2B3C
     */
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    public void print(int printNumber){
        int x = 0;
        if (printNumber == 5){
            x = 1;
        }else if (printNumber == 10){
            x = 2;
        }else if (printNumber ==15){
            x = 3;
        }
        if (x==0){
            return;
        }
        lock.lock();
        try{
            // 判断
            while (number !=x){
                if (x == 1){
                    condition1.await();
                }else if (x == 2) {
                    condition2.await();
                }else {
                    condition3.await();
                }
            }
            // 干活
            for (int i = 1; i <= printNumber; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 通知
            if (x == 1){
                number = 2;
                condition2.signal();
            }else if (x == 2) {
                number = 3;
                condition3.signal();
            }else {
                number = 1;
                condition1.signal();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print(5);
            }
        },"AA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print(10);
            }
        },"BB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print(15);
            }
        },"CC").start();
    }
}
