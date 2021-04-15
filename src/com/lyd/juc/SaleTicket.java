package com.lyd.juc;

/**
 * @Author Liuyunda
 * @Date 2021/4/15 23:00
 * @Email man021436@163.com
 * @Description: 三个售票员 卖出 30张票
 * 1.在高内聚低耦合的前提下，线程  操作(对外暴露的调用方法)  资源类
 */
class Ticket{
    private int number = 30;
    public synchronized void saleTicket(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+"\t还剩下："+number);
        }
    }
}
public class SaleTicket {
    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        // Thread(Runnable target, String name)
        // 分配一个新的 Thread对象。
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 40; i++) {
                    ticket.saleTicket();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 40; i++) {
                    ticket.saleTicket();
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 40; i++) {
                    ticket.saleTicket();
                }
            }
        },"C").start();
    }
}
