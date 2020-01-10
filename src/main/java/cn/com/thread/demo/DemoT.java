package cn.com.thread.demo;


import java.util.concurrent.CountDownLatch;

public class DemoT {

    private Object obj = new Object();


    public static void main(String[] args)throws Exception {

        DemoT demoT = new DemoT();
        /*
        Thread thread1 = new Thread(demoT.new JISHU(demoT.obj));
        Thread thread2 = new Thread(demoT.new OUSHU(demoT.obj));

        thread1.start();
        thread2.start();
        */
        Thread thread1 = new Thread(demoT.new DemoTT(demoT.obj,1));
        Thread thread2 = new Thread(demoT.new DemoTT(demoT.obj,2));
        CountDownLatch countDownLatch = new CountDownLatch(1);
        thread1.start();
        thread1.start();
        //countDownLatch.countDown();
        //countDownLatch.await();
        //保证thread1 能先于thread2 执行
        Thread.sleep(100);
        //thread2.start();
    }

    public class DemoTT implements Runnable{
        private Object obj;
        private int start;
        private int max;

        public DemoTT(Object obj,int type){
            this.obj = obj;
            if(type == 1){
                start = 1;
                max = 99;
            }else if(type == 2){
                start = 2;
                max = 100;
            }
        }

        @Override
        public void run() {
            while(true){
                synchronized (obj){
                    obj.notify();
                    System.out.println(start);
                    start+=2;
                    if(start > max){
                        break;
                    }
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public class JISHU implements Runnable{
        private Object obj;
        public JISHU(Object obj){
            this.obj = obj;
        }

        @Override
        public void run() {
            int i = 1;
            while(true){
                synchronized (obj){
                    obj.notify();
                    System.out.println(i);
                    i+=2;
                    if(i > 99){
                        break;
                    }
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public class OUSHU implements Runnable{
        private Object obj;
        public OUSHU(Object obj){
            this.obj = obj;
        }

        @Override
        public void run() {
            int i = 2;
            while(true){
                synchronized (obj){
                    obj.notify();
                    System.out.println(i);
                    i+=2;

                    if(i > 100){
                        break;
                    }

                    try {

                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
