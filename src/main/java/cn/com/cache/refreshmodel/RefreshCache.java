package cn.com.cache.refreshmodel;

import redis.clients.jedis.Jedis;

public class RefreshCache {

    private Jedis jedis;

    public RefreshCache(Jedis jedis) {
        this.jedis = jedis;
    }

    public void refresh(){
        //模拟删除缓存
        jedis.del("testCache");
        //模拟从db中取值
        long result = System.currentTimeMillis();
        //模拟更新缓存
        jedis.set("testCache",result+"");

        System.out.println("更新成功!");
    }
}
