package com.lyd.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;


class MyTask extends RecursiveTask<Integer>{
    private static final Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - begin <= ADJUST_VALUE) {
            for (int i = begin; i <= end ; i++) {
                result += i;
            }
        }else {
            int middle = (end +begin)/2;
            MyTask myTask = new MyTask(begin,middle);
            MyTask myTask2 = new MyTask(middle+1,end);
            myTask.fork();
            myTask2.fork();
            result  = myTask.join()+myTask2.join();
        }
        
        return result;
    }
}
/**
 * @Author Liuyunda
 * @Date 2021/5/9 21:53
 * @Email man021436@163.com
 * @Description: 分支合并框架
 *
 * ForkJoinPool
 * ForkJoinTask
 * RecursiveTask
 *
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyTask myTask = new MyTask(0, 10);
        ForkJoinPool threadPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);
        System.out.println(forkJoinTask.get());
        threadPool.shutdown();

    }
}
