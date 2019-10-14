package cn.com.executor.impl;

import java.util.concurrent.Callable;

public class AddCountExecutor implements Callable<Integer> {

    private int start;
    private int end;
    public AddCountExecutor(int start,int end){
        this.start = start;
        this.end = end;
    }
    @Override
    public Integer call() throws Exception {
        int temp = 0;

        for(int i=0;i<=(end-start);i++){
           temp += (start+i);
        }
        return temp;
    }
}
