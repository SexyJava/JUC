package com.lyd.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author Liuyunda
 * @Date 2021/4/25 20:16
 * @Email man021436@163.com
 * @Description: 抢车位
 *      在信号量上我们定义两种操作：
 *      acquire（获取）：当一个线程调用acquire操作时，他要么通过成功获取信号量（信号量减1），要么一直等下去，知道有线程释放信号量，或超时
 *      release（释放）：实际上会将信号量的值加1，然后唤醒等待的线程。
 *      信号量主要用于两个目的：一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 3个车位
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t停车");
                    try { TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e) { e.printStackTrace(); }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName()+"\t走了");
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
