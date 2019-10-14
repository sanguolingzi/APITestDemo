package cn.com.thread.CountDownLatch;

import java.util.concurrent.CountDownLatch;


/**
 *  线程先执行  之后 阻塞主线程 直到    countDownLatch.await() 满足条件
 */
public class Test {
    public static void main(String[] args) throws Exception{
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("线程1准备!");
                    countDownLatch.countDown();
                    System.out.println("线程1执行完了!");
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("线程2准备!");
                    countDownLatch.countDown();
                    System.out.println("线程2执行完了!");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        thread2.start();

        countDownLatch.await();
        System.out.println("所有工作执行完毕!");

    }
}
