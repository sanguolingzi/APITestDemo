package cn.com.jedis.list;

import redis.clients.jedis.Jedis;

public class Test {
    public static void main(String[] args) throws Exception{
        Jedis jedis = new Jedis("localhost",6379);
        //建立一个list  模拟的栈操作
        //lpush   rpop    实现先进先出的效果
        jedis.del("list");
        System.out.println(jedis.lrange("list",0,100));
        jedis.lpushx("list","1");//?
        jedis.lpush("list","a");
        System.out.println(jedis.lrange("list",0,100));
        jedis.lrem("list",0,"a");
        //remove
        System.out.println(jedis.lrange("list",0,100));
    }
}
