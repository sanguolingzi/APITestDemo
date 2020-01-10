package cn.com.jedis.cluster;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

public class testMain {

    public static void main(String[] args) {
       /* PoolUtil poolUtil = new PoolUtil();
        ShardedJedis shardedJedis = poolUtil.getResource();
        System.out.println(shardedJedis.get("a"));
        shardedJedis.close();*/
        JedisCluster jedisCluster = ClusterPool.getJedisCluster();
        /*System.out.println(jedisCluster.get("cd"));
        System.out.println(jedisCluster.get("cd1"));
        System.out.println(jedisCluster.get("cd2"));
        System.out.println(jedisCluster.get("cd3"));*/

        //jedisCluster.set("{b}cd","cd");
        jedisCluster.expire("{b}cd",10);
        jedisCluster.expire("{b}a",10);
        //jedisCluster.set("{b}a","aaaaaaaaaaaa");
        System.out.println("here");
    }
}
