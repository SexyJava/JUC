package com.lyd.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Liuyunda
 * @Date 2021/6/17 22:53
 * @Email man021436@163.com
 * @Description: TODO
 */
class MyResource{
    // 默认开启生产消费
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProduc() throws Exception{
        String data = null;
        boolean returnValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet()+"";
            returnValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if (returnValue) {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 大老板叫停了，标识Flag = false，生产动作结束");
    }
    public void myConsumer() throws InterruptedException {
        String value = null;
        while (FLAG) {
            value = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == value || value.equals("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过2秒没有取到数据，消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+value+"成功");
        }
    }

    public void stop() throws Exception{
        this.FLAG = false;
    }
}
public class BolckingQueueProducConsumer {

    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10,false));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            try {
                myResource.myProduc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Produc").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        try { TimeUnit.SECONDS.sleep(5); }catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("5秒时间到，大老板Main线程叫停，活动结束");
        myResource.stop();
    }
}
