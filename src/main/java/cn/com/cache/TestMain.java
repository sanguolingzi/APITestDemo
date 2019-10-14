package cn.com.cache;

import redis.clients.jedis.Jedis;

import java.util.Objects;

public class TestMain {
    public static void main(String[] args) throws Exception{



        //search
        //更新缓存
        //1、删除缓存
        //2、更新db
        //3、更新缓存


    }


    public static void search() throws Exception{

    }


    public static void refreshCache() throws Exception{
        Jedis jedis = new Jedis("localhost",6379);

        //模拟删除缓存
        jedis.del("testCache");
        //模拟从db中取值
        long result = System.currentTimeMillis();
        //模拟更新缓存
        jedis.set("testCache",result+"");

    }

    public static void doBusi(){

        Jedis jedis = new Jedis("localhost",6379);





    }
}
