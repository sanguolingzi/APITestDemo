package cn.com.executor;

import cn.com.executor.comple.TestCompleExecutor;
import cn.com.executor.impl.AddCountExecutor;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws Exception{
        //addCount();
        competeThread();
    }

    /**
     * 做一个 1 到 100的 并发累加
     * @throws Exception
     */
    public static void addCount() throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<Integer> callable = new AddCountExecutor(1,50);
        Callable<Integer> callable2 = new AddCountExecutor(51,100);


        List<Callable<Integer>> list = Lists.newArrayList();
        list.add(callable);
        list.add(callable2);

        List<Future<Integer>> futures =  executorService.invokeAll(list);

        int result = 0;
        for(Future<Integer> future:futures){
            System.out.println(result+"......."+  (result +=future.get()));
        }

        System.out.println("result:"+result);
        executorService.shutdown();
    }


    /**
     * executorService 获取线程执行结果 获取的顺序与执行submit的顺序一致
     * completionService 获取线程执行结果 优先获取的是任务已结束的
     *
     * @throws Exception
     */
    public static void  competeThread() throws Exception{

       /* ExecutorService executorService = Executors.newCachedThreadPool();

        TestCompleExecutor t1 = new TestCompleExecutor(1);
        TestCompleExecutor t2 = new TestCompleExecutor(2);
        List<Callable<String>> list = Lists.newArrayList(t2,t1);
        List<Future<String>> result = executorService.invokeAll(list);

        System.out.println("executorService:");
        for(Future<String> future:result){
            System.out.println(future.get());
        }

        CompletionService<String> completionService = new ExecutorCompletionService<String>(executorService);


        TestCompleExecutor t11 = new TestCompleExecutor(1);
        TestCompleExecutor t22 = new TestCompleExecutor(2);
        completionService.submit(t1);
        completionService.submit(t2);

        System.out.println("completionService:");
        for(int i=0;i<2;i++){
            System.out.println(completionService.take().get());
        }
        executorService.shutdown();*/
    }
    //forkJoinPool
}
