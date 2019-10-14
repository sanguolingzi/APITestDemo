package cn.com.cache.refreshmodel;

import redis.clients.jedis.Jedis;

import java.util.Objects;

public class SearchCache {

    private Jedis jedis;

    public SearchCache(Jedis jedis) {
        this.jedis = jedis;
    }

    public Object search() throws Exception {
        //读取缓存 若不存在 发送一个更新缓存请求
        //然后轮询缓存 存在结果则 返回结果 若指定时间内不存在 则查询db 返回结果
        String result = jedis.get("testCache");
        if (Objects.isNull(result)) {

            //发送更新缓存请求
            //TODO
            Object flag = jedis.get("refreshFlag");
            if (Objects.isNull(flag)) {
                flag = "1";
                jedis.setnx("refreshFlag", flag.toString());
            }

            jedis.lpush("refreshQueue", flag.toString());
            int start = 1;
            //轮询
            while (true) {
                result = jedis.get("testCache");
                if (Objects.isNull(result)) {
                    //判断是否超时
                    if (start > 10) {
                        //模拟查询db 直接返回结果
                        System.out.println("--查询读取到结果--:123");
                        return "123";
                    } else {
                        start++;
                        Thread.sleep(1000);
                    }
                } else {
                    System.out.println("--轮询取到结果--:" + result);
                    break;
                }
            }
        } else {
            System.out.println("--直接读取到结果--:" + result);
        }
        return result;
    }

}

