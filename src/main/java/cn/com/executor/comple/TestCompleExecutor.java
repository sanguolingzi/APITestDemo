package cn.com.executor.comple;

import java.util.concurrent.Callable;

public class TestCompleExecutor implements Callable<String> {

    private int flag;
    public TestCompleExecutor(int flag){
        this.flag = flag;
    }

    @Override
    public String call() throws Exception {

        if(flag%2==0){
            Thread.sleep(3000);
        }else{
            Thread.sleep(1000);
        }

        return (flag%2==0)?"休眠3秒结束":"休眠1秒结束";
    }
}
