package com.lyd.juc;

/**
 * @Author Liuyunda
 * @Date 2021/4/20 20:10
 * @Email man021436@163.com
 * @Description: 现在两个线程，可以操作初始值为0的一个变量，实现一个线程对该变量加1，一个线程对该变量减1，实现交替，来10轮，变量初始值为0
 * 1. 判断/干活/通知
 * 2. 多线程交互中，必须要防止多线程的虚假唤醒，也即判断不能使用if只能使用while
 */
class AirConditioner{
    private int number = 0;

    public synchronized void jia() throws InterruptedException {
        // 判断
        while (number != 0){
            this.wait();
        }
        // 干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        // 通知
        this.notifyAll();

    }
    public synchronized void jian() throws InterruptedException {
        // 判断
        while (number == 0){
            this.wait();
        }
        // 干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        // 通知
        this.notifyAll();

    }
}
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();
        new Thread(() -> {
            // TODO
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(400);
                    airConditioner.jia();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(() -> {
            // TODO
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                    airConditioner.jian();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(() -> {
            // TODO
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                    airConditioner.jia();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(() -> {
            // TODO
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                    airConditioner.jian();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
