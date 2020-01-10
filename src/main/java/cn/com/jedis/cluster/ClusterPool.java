package cn.com.jedis.cluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

public class ClusterPool {

    private static volatile JedisCluster jedisCluster;
    public ClusterPool(){
        //initPool();
    }

    public static JedisCluster getJedisCluster() {
        if(jedisCluster == null){
            synchronized (ClusterPool.class){
                if(jedisCluster == null){
                    initPool();
                }
            }
        }
        return jedisCluster;
    }

    public static void initPool(){
        HostAndPort hostAndPort2 = new HostAndPort("175.24.128.108", 8002);
        HostAndPort hostAndPort1 = new HostAndPort("118.25.38.99", 8001);
        HostAndPort hostAndPort3 = new HostAndPort("175.24.128.108", 8003);
        Set<HostAndPort> nodeSet = new HashSet<HostAndPort>(3);
        nodeSet.add(hostAndPort1);
        nodeSet.add(hostAndPort2);
        nodeSet.add(hostAndPort3);

        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        //是否启用后进先出, 默认true
        config.setLifo(true);
        //最大空闲连接数, 默认8个
        config.setMaxIdle(10);
        //最大连接数, 默认8个
        config.setMaxTotal(50);
        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        config.setMaxWaitMillis(500);
        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        //config.setMinEvictableIdleTimeMillis(1800000);
        //最小空闲连接数, 默认0
        //config.setMinIdle(1);
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        //config.setNumTestsPerEvictionRun(3);
        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
        //config.setSoftMinEvictableIdleTimeMillis(1800000);
        //在获取连接的时候检查有效性, 默认false
        //config.setTestOnBorrow(true);
        //在空闲时检查有效性, 默认false
        //config.setTestWhileIdle(true);
        jedisCluster = new JedisCluster(nodeSet,500,500,2,"Hh10701",config);
        //jedisCluster = new JedisCluster(nodeSet,config);
    }
}
