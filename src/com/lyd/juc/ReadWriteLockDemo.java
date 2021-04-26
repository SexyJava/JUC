package com.lyd.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();
    private  ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value) {
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t---写入数据"+key);
            try { TimeUnit.MILLISECONDS.sleep(300); }catch (InterruptedException e) { e.printStackTrace(); }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t---写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }
    public void get(String key) {
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t---读取数据");
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t---读取完成"+o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}
/**
 * @Author Liuyunda
 * @Date 2021/4/26 19:57
 * @Email man021436@163.com
 * @Description: 读写锁
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是如果有一个线程想去写共享资源类，就不应该再有其他线程可以对该资源进行读或写
 * 读读共存
 * 读写不能共存
 * 写写不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int x = i;
            new Thread(() -> {
                myCache.put(x+"",x+"");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int x = i;
            new Thread(() -> {
                myCache.get(x+"");
            },String.valueOf(i)).start();
        }
    }
}
