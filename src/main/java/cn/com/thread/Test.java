package cn.com.thread;

import cn.com.thread.executors.AverageFurture;
import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;
import java.lang.Thread;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    static final String s = "asd";

    public static void main(String[] args) throws Exception{

        List list = Lists.newArrayList();
        for(int i=0;i<14400;i++){
            list.add((i+1));
        }
        int sep = 3;
        //任务数量
        int part = 0;
        if(list.size()%sep==0){
            part = list.size()/sep;
        }else{
            part = list.size()/sep + 1;
        }
        System.out.println("开始时间:"+LocalDateTime.now());
        System.out.println("cpu:"+Runtime.getRuntime().availableProcessors());
        //ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        ExecutorService executorService =  new ThreadPoolExecutor(
        2,
        2,
        0,
        TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(5),
        //new ThreadPoolExecutor.AbortPolicy()
        //new ThreadPoolExecutor.DiscardPolicy()
        new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        List<Future<Double>> futures = Lists.newArrayList();
        try{
            for(int i=0;i<part;i++){
                AverageFurture averageFurture = new AverageFurture(list,(i+1),sep);
                futures.add(executorService.submit(averageFurture));
            }

            System.out.println("size:"+futures.size());

            for(Future<Double> doubleFuture:futures){
                System.out.println(doubleFuture.get());
            }
            System.out.println("结束时间:"+LocalDateTime.now());
        }catch (RejectedExecutionException ee){
            ee.printStackTrace();
        }
        FutureTask futureTask =  new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "asd";
            }
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
        //executorService.shutdown();

        ThreadPoolExecutor tpe = (ThreadPoolExecutor)executorService;
        while (true) {
            System.out.println();
            int queueSize = tpe.getQueue().size();
            System.out.println("当前排队线程数：" + queueSize);

            int activeCount = tpe.getActiveCount();
            System.out.println("当前活动线程数：" + activeCount);

            long completedTaskCount = tpe.getCompletedTaskCount();
            System.out.println("执行完成线程数：" + completedTaskCount);

            long largetestSize = tpe.getLargestPoolSize();
            System.out.println("线程最大数目：" + largetestSize);
            long taskCount = tpe.getTaskCount();
            System.out.println("总线程数：" + taskCount);

            Thread.sleep(10);
        }




    }
}
