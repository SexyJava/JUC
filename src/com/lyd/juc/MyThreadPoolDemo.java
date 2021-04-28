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

        /**
         * 七大参数
         * 1.corePoolSize 常驻核心线程数
         * 2.maximumPoolSize 最大线程数
         * 3.keepAliveTime 空闲线程等待的最长时间
         * 4.unit 空闲线程等待的时间单位
         * 5.workQueue 任务队列（等待队列）
         * 6.threadFactory 创建线程池使用的工厂
         * 7.handler 池及队列都满了后，执行的拒绝策略（拒绝方法）
         */
        // 最大容纳线程数等于maximumPoolSize+workQueue的容量
        Runtime.getRuntime().availableProcessors();

        /**
         * 如果系统属于CPU密集型maximumPoolSize = Runtime.getRuntime().availableProcessors()（cpu逻辑处理器个数）+1
         * 如果属于IO密集型（磁盘IO（磁盘的读写），网络IO（http请求，远程 数据库读写等））maximumPoolSize = 2*Runtime.getRuntime().availableProcessors() +1
         * 最佳线程数目 = （（线程等待时间+线程CPU时间）/线程CPU时间 ）* CPU数目
         * 优化为：最佳线程数目 = （线程等待时间与线程CPU时间之比 + 1）* CPU数目
         * 结论：线程等待时间所占比例越高，需要越多线程。线程CPU时间所占比例越高，需要越少线程。
         */
        ExecutorService myExecutor = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors()+1,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        /**
         * 4种拒绝策略
         * AbortPolicy(默认): 直接抛出RejectedExecutionException异常阻止系统正常运行
         * CallerRunsPolicy: “调用者运行”一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量
         * DiscardOldestPolicy: 抛弃队列中等待最久的任务，然后把当前任务加入到队列中，尝试再次提交当前任务
         * DiscardPolicy: 该策略默默地丢弃无法处理的任务，不予任何处理也不抛出异常。如果允许任务丢失，这是最好的一种策略
         */


        try{
            for (int i = 0; i < 10; i++) {
                myExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t get");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myExecutor.shutdown();
        }
    }

    private static void initPool() {
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
