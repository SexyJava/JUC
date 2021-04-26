package com.lyd.juc;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author Liuyunda
 * @Date 2021/4/26 20:28
 * @Email man021436@163.com
 * @Description: 阻塞队列
 * 队列为空时不能获取
 * 队列满时不能插入
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> stringBlockingQueue = new ArrayBlockingQueue<>(3);
        // 抛出异常组
        // System.out.println(stringBlockingQueue.add("a"));
        // System.out.println(stringBlockingQueue.add("b"));
        // System.out.println(stringBlockingQueue.add("c"));
        // System.out.println(stringBlockingQueue.add("d"));
        // System.out.println(stringBlockingQueue.element());
        // for (int i = 0; i < 4; i++) {
        //     System.out.println(stringBlockingQueue.remove());
        // }

        // 特殊值组
        // System.out.println(stringBlockingQueue.offer("a"));
        // System.out.println(stringBlockingQueue.offer("b"));
        // System.out.println(stringBlockingQueue.offer("c"));
        // System.out.println(stringBlockingQueue.offer("d"));
        //
        // System.out.println(stringBlockingQueue.peek());
        // for (int i = 0; i < 4; i++) {
        //     System.out.println(stringBlockingQueue.poll());
        // }
        // 阻塞组
        // stringBlockingQueue.put("a");
        // stringBlockingQueue.put("b");
        // stringBlockingQueue.put("c");
        // stringBlockingQueue.put("d");

        // for (int i = 0; i < 3; i++) {
        //     System.out.println(stringBlockingQueue.take());
        // }

        // 超时组
        System.out.println(stringBlockingQueue.offer("a", 3L, TimeUnit.SECONDS));
        System.out.println(stringBlockingQueue.offer("b", 3L, TimeUnit.SECONDS));
        System.out.println(stringBlockingQueue.offer("c", 3L, TimeUnit.SECONDS));
        System.out.println(stringBlockingQueue.offer("d", 3L, TimeUnit.SECONDS));

        for (int i = 0; i < 4; i++) {
            System.out.println(stringBlockingQueue.poll(3L, TimeUnit.SECONDS));
        }
    }
}
