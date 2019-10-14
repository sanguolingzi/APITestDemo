package cn.com.thread;

import cn.com.thread.executors.AverageFurture;
import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

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
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
        List<Future<Double>> futures = Lists.newArrayList();
        for(int i=0;i<part;i++){
            AverageFurture averageFurture = new AverageFurture(list,(i+1),sep);
            futures.add(executorService.submit(averageFurture));
        }
        for(Future<Double> doubleFuture:futures){
            System.out.println(doubleFuture.get());
        }
        executorService.shutdown();
        System.out.println("结束时间:"+LocalDateTime.now());
    }
}
