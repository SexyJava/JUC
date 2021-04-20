package com.lyd.juc;

/**
 * @Author Liuyunda
 * @Date 2021/4/20 20:10
 * @Email man021436@163.com
 * @Description: 现在两个线程，可以操作初始值为0的一个变量，实现一个线程对该变量加1，一个线程对该变量减1，实现交替，来10轮，变量初始值为0
 * 1. 判断/干活/通知
 */
class AirConditioner{
    private int number = 0;

    public synchronized void jia() throws InterruptedException {
        // 判断
        if (number == 0){
            // 干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 通知
            this.notifyAll();
        }else {
            this.wait();
        }
    }
    public synchronized void jian() throws InterruptedException {
        // 判断
        if (number != 0){
            // 干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 通知
            this.notifyAll();
        }else {
            this.wait();
        }
    }
}
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();
        new Thread(() -> {
            // TODO
            for (int i = 0; i < 10; i++) {
                try {
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
                    airConditioner.jian();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }
}
