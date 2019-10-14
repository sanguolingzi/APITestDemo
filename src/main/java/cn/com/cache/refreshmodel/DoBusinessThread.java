package cn.com.cache.refreshmodel;

import redis.clients.jedis.Jedis;

import java.math.BigDecimal;

public class DoBusinessThread implements Runnable{
    private Jedis jedis;

    public DoBusinessThread(Jedis jedis) {
        this.jedis = jedis;
    }


    @Override
    public void run() {

        while(true){
            try {
                //待更新业务队列
                Object refreshQueue = jedis.rpop("refreshQueue");

                //最新的更新标记
                Object refreshFlag = jedis.get("refreshFlag");

                //那么当refreshQueue 小于 refreshFlag 时候  意味着 这个更新操作已被执行 直接忽略


                BigDecimal a = new BigDecimal(refreshQueue.toString());
                BigDecimal b = new BigDecimal(refreshFlag.toString());

                if(a.compareTo(b) == 0){




                }



                Thread.sleep(500);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
