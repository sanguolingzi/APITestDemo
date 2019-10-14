package cn.com.thread.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 阻塞子线程 直到cyclicBarrier.await() 满足条件之后 再执行
 */
public class Test {

    public static void main(String[] args) throws Exception{

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            public void run() {
                System.out.println("所有都准备完成!");
            }
        });

        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("线程1准备!");
                    cyclicBarrier.await();
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
                    cyclicBarrier.await();
                    System.out.println("线程2执行完了!");
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        thread.start();
        thread2.start();

    }
}
