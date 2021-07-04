package com.lyd.juc;

import java.util.concurrent.TimeUnit;

/**
 * @Author Liuyunda
 * @Date 2021/7/5 0:00
 * @Email man021436@163.com
 * @Description: TODO
 */
public class DeadLock {

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + " get a");
                try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "want get b");
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + " get b");
                }
            }

        }, "线程1").start();
        new Thread(() -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + " get b");
                try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "want get a");
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + " get a");
                }
            }

        }, "线程2").start();

    }
}
