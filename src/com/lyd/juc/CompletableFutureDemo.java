package com.lyd.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author Liuyunda
 * @Date 2021/5/9 22:17
 * @Email man021436@163.com
 * @Description: 异步回调
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
        //     System.out.println(Thread.currentThread().getName() + "\t没有返回值");
        // });
        // completableFuture.get();

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t有返回值");
            int x = 10/0;
            return 1024;
        });
        System.out.println(completableFuture1.whenComplete((t, u) -> {
            System.out.println("****t\t" + t);
            System.out.println("****u\t" + u);
        }).exceptionally(f -> {
            System.out.println("****Exception\t" + f.getMessage());
            return 444;
        }).get());

    }
}
