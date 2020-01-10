package cn.com.testjvm;

import java.util.HashMap;
import java.util.Map;
import java.util.Hashtable;

public class TestMain {

    static int M = 1024*1024;

    /**
     * -XX:+PrintGCDetails -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
     * -XX:HeapDumpPath=C:\\Users\\Administrator\\Desktop\\dump快照\\gc.hprof
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        t1();
        for(;;){

        }
        /*Map hashMap = new HashMap();
        hashMap.put(null,"1");

        Map hash = new Hashtable();
        hash.put(null,"a");
        System.out.println(hash.get(null));*/

    }

    /**
     *
     */
    public static void t1(){
        byte[] b = new byte[M*1];
        byte[] b1 = new byte[M*1];
        byte[] b2 = new byte[M*1];
        byte[] b4 = new byte[M*1];
        byte[] b5 = new byte[M*5];
    }
}
