package cn.com.jedis.cluster;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

public class PoolUtil {

    ShardedJedisPool shardedJedisPool;

    public PoolUtil(){
        initialShardedPool();
    }

    /**
       * 初始化切片池
       */
    private void initialShardedPool() {
            // 池基本配置
            JedisPoolConfig config = new JedisPoolConfig();
            //是否启用后进先出, 默认true
            config.setLifo(true);
            //最大空闲连接数, 默认8个
            config.setMaxIdle(8);
            //最大连接数, 默认8个
            config.setMaxTotal(50);
            //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
            config.setMaxWaitMillis(2000);
            //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
            config.setMinEvictableIdleTimeMillis(1800000);
            //最小空闲连接数, 默认0
            config.setMinIdle(0);
            //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
            config.setNumTestsPerEvictionRun(3);
            //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
            config.setSoftMinEvictableIdleTimeMillis(1800000);
            //在获取连接的时候检查有效性, 默认false
            config.setTestOnBorrow(false);
            //在空闲时检查有效性, 默认false
            config.setTestWhileIdle(false);

            // slave链接
            List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
            //(存在部分问题)

            JedisShardInfo jedisShardInfo = new JedisShardInfo("118.25.38.99", 8001);
            jedisShardInfo.setPassword("Hh10701");
            shards.add(jedisShardInfo);
            jedisShardInfo = new JedisShardInfo("175.24.128.108", 8002);
            jedisShardInfo.setPassword("Hh10701");
            shards.add(jedisShardInfo);
            jedisShardInfo = new JedisShardInfo("175.24.128.108", 8003);
            jedisShardInfo.setPassword("Hh10701");
            shards.add(jedisShardInfo);
            shardedJedisPool = new ShardedJedisPool(config, shards);
            // 构造池
            // shardedJedisPool =new ShardedJedisPool(config, shards, Hashing.MURMUR_HASH,Sharded.DEFAULT_KEY_TAG_PATTERN);
    }

    // 获取连接(1)
    public ShardedJedis getResource() {
        ShardedJedis jedis = shardedJedisPool.getResource();
        //jedis.auth(pwd);
        return jedis;
    }

}
