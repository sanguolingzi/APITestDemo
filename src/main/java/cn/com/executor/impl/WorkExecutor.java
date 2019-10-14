package cn.com.executor.impl;

import com.google.common.collect.Maps;

import java.util.concurrent.Callable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WorkExecutor implements Callable<Map<String,Object>>{

    ConcurrentHashMap<String,Integer> data;
    public WorkExecutor(ConcurrentHashMap<String,Integer> data){
        this.data = data;
    }

    public WorkExecutor(){
    }

    @Override
    public Map<String, Object> call() throws Exception {
        Map<String, Object> s = Maps.newHashMap();
        s.put("asd",System.currentTimeMillis());
        return s;
    }
}
