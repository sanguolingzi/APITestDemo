package cn.com.thread.executors;


import java.util.List;
import java.util.concurrent.Callable;

public class AverageFurture implements Callable<Double> {

    private List<Integer> list;
    private int part;
    private int sep;

    public AverageFurture(List<Integer> list,int part,int sep){
        this.list = list;
        this.part = part;
        this.sep = sep;
    }


    public Double call() throws Exception {
        int start = (part-1)*sep;
        int end = part*sep;
        if(list.size() < end){
            end = list.size();
        }
        int sum =list.subList(start,end).stream().reduce(0,(a,b) -> Integer.sum(a,b));
        return new Double(sum)/sep;
    }
}
