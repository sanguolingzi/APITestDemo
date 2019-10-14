package cn.com.jedis.hash;

import com.google.common.collect.Maps;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * redis hash结构  直接通过api 操作类似map结构的属性
 * 如 更改 key 为 user 的 属性name
 */
//上面已经说到Redis Hash对应Value内部实际就是一个HashMap，实际这里会有2种不同实现，
//这个Hash的成员比较少时Redis为了节省内存会采用类似一维数组的方式来紧凑存储，
// 而不会采用真正的HashMap结构，对应的value redisObject的encoding为zipmap,
// 当成员数量增大时会自动转成真正的HashMap,此时encoding为ht。
public class Test {
    public static void main(String[] args) throws Exception{

        Jedis jedis = new Jedis("localhost",6379);


        Map<String,String> proper = Maps.newHashMap();
        proper.put("age","12");
        proper.put("name","测试");
        proper.put("id","123");

        //jedis.hset("123",proper);
        jedis.hset("123","age","12");
        jedis.hset("123","name","测试");
        System.out.println(jedis.hget("123","name"));
        System.out.println(jedis.hgetAll("123"));
    }
}
