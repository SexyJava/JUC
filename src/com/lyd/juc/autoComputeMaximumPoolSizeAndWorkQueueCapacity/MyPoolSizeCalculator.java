package com.lyd.juc.autoComputeMaximumPoolSizeAndWorkQueueCapacity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author Liuyunda
 * @Date 2021/4/28 21:53
 * @Email man021436@163.com
 * @Description: 线程池的队列容量及最大线程数推荐方法
 * 摘自【如何合理地估算线程池大小？-并发编程网】
 */
public class MyPoolSizeCalculator extends PoolSizeCalculator{
    public static void main(String[] args) {
        MyPoolSizeCalculator myPoolSizeCalculator = new MyPoolSizeCalculator();
        myPoolSizeCalculator.calculateBoundaries(new BigDecimal(1.0), new BigDecimal(100000));
    }
    @Override
    protected Runnable creatTask() {
        AsynchronousTask asynchronousTask = new AsynchronousTask();
        return new Thread(()->{
            asynchronousTask.sendRequest();
        });
    }

    @Override
    protected BlockingQueue<Runnable> createWorkQueue() {
        return new LinkedBlockingQueue<>();
    }

    /**
     * @Description: 获取当前线程的CPU时间
     * @Param: []
     * @return: long
     * @Author: Liuyunda
     * @Date: 2021/4/28
     */
    @Override
    protected long getCurrentThreadCPUTime() {
        return ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
    }
}
class AsynchronousTask {
    void sendRequest(){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try{
            String getURL = "https://www.baidu.com/";
            URL getUrl = new URL(getURL);
            connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                }
                catch(Exception e) {
                }
            }
            connection.disconnect();

        }
    }
}
