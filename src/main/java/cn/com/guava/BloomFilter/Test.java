package cn.com.guava.BloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

public class Test {

    public static int size = 1000*1000;
    public static void main(String[] args) throws Exception{
        // funnel（输入的数据
        // expectedInsertions（预计插入的元素总数）
        // fpp（期望误判率）
        // strategy（实现Strategy的实例）
        BloomFilter<Integer> dealIdBloomFilter = BloomFilter.create(new Funnel<Integer>() {
            private static final long serialVersionUID = 1L;
            public void funnel(Integer arg0, PrimitiveSink arg1) {
                arg1.putInt(arg0);
            }
        }, size,0.0001D);

        for(int i=0;i<size;i++){
            dealIdBloomFilter.put(i);
        }

        int result = 0;
        for(int i=size+10000;i<size+20000;i++){
           if(!dealIdBloomFilter.mightContain(i)){
               result ++;
           }
        }
        System.out.println(result);
    }
}
