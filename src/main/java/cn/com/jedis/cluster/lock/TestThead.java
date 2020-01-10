package cn.com.jedis.cluster.lock;

import cn.com.jedis.cluster.ClusterPool;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.params.SetParams;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThead implements Callable{

    private String _lock ="_lock";
    private JedisCluster jedisCluster;
    public TestThead(){
        jedisCluster =  ClusterPool.getJedisCluster();
    }
    //模拟分布式锁
    //set方法 判断是否存在 以及设置 锁的存活时间 value为持有锁的证明
    //释放所 需要获取锁信息 并且保证value与本地保持一致


    @Override
    public Object call() throws Exception {
        //持有锁的证明
        String uuid = UUID.randomUUID().toString();

        SetParams setParams = new SetParams();
        setParams.ex(30);
        setParams.nx();
        String result = null;
        while(result == null){
            result = jedisCluster.set(_lock,uuid,setParams);
            if(result != null){
                System.out.println(result+"....."+uuid+"抢到锁了!");

                //模拟业务处理耗时
                Thread.sleep(200);


                //准备释放锁
                //获取锁
                String _uuid = jedisCluster.get(_lock);
                //证明是否是自己持有的锁
                if(_uuid != null && _uuid.equalsIgnoreCase(uuid)){
                    //释放锁
                    System.out.println(uuid+"成功释放锁了!");
                    jedisCluster.del(_lock);
                    //跳出循环
                    break;
                }else{
                    //不做事情
                }

            }else{
                //锁被其它线程持有 等一会
                Thread.sleep(100);
            }
        }
        //System.out.println(Thread.currentThread().getName()+"结束!");
        return null;
    }


    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4,
                50,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1000),
                new ThreadPoolExecutor.AbortPolicy());

        System.out.println(LocalDateTime.now());

        for(int i= 0;i<200;i++){
            threadPoolExecutor.submit(new TestThead());
        }
        threadPoolExecutor.shutdown();

       /* JedisCluster jedisCluster = ClusterPool.getJedisCluster();

        SetParams setParams = new SetParams();
        setParams.ex(30);
        setParams.nx();*/

        //String result = jedisCluster.set("_lock",UUID.randomUUID().toString(),setParams);
        //System.out.println("result:"+(result==null));
    }
}
