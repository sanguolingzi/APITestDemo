package cn.com.jedis.set;

import redis.clients.jedis.Jedis;

public class Test {

    public static void main(String[] args) throws Exception{

        Jedis jedis = new Jedis("118.25.38.99 ",8001);
        jedis.auth("Hh10701");

        //初始化
        /*
        jedis.zadd("testSet",2,"a");
        jedis.zadd("testSet",3,"b");
        jedis.zadd("testSet",4,"c");
        jedis.zadd("testSet",5,"d");
        */
        //获取所有集合内容 可实现分页查询
        System.out.println(jedis.zrange("testSet",0,100));//返回[a,b,c,d]

        //获取长度
        System.out.println(jedis.zcard("testSet"));//4

        //根据score内容获取指定区间的长度
        System.out.println(jedis.zcount("testSet",2,3));//2
        //根据score内容获取指定区间的长度
        System.out.println(jedis.zcount("testSet","2","4"));//3
        //
        System.out.println(jedis.zlexcount("testSet","[a","[b"));//2

        //根据 member内容 查询区间段 实现一个条件查询
        //- 代表得分最小值  +得分最大值
        System.out.println(jedis.zrangeByLex("testSet","-","+"));//a,b,c,d
        //[ 是闭区间 不包括
        System.out.println(jedis.zrangeByLex("testSet","[a","[d"));//a,b,c,d
        //( 是开区间 不包括
        System.out.println(jedis.zrangeByLex("testSet","(a","[d"));//b,c,d

        //按照分数 score字段 实现一个条件查询
        System.out.println(jedis.zrangeByScore("testSet",1,5));//a,b,c,d
        //按照分数 score字段
        System.out.println(jedis.zrangeByScore("testSet","1","4"));//a,b,c,d


    }
}
