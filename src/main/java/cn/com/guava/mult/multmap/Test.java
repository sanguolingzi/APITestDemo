package cn.com.guava.mult.multmap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;

public class Test {
    public static void main(String[] a) throws Exception {
        //实现的类似 map<k,List<T>>的效果
        //类似的还有 HashMultimap TreeMultimap 等等
        ArrayListMultimap arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.put("a","b");
        System.out.println(arrayListMultimap);
        arrayListMultimap.put("a","c");
        System.out.println(arrayListMultimap);
        arrayListMultimap.put("a","d");
        System.out.println(arrayListMultimap);

        System.out.println(arrayListMultimap.get("a"));

        //实现Map<r,Map<c,v>> 的效果
        HashBasedTable hashBasedTable = HashBasedTable.create();
        hashBasedTable.put("a","aa","1");
        hashBasedTable.put("a","aa","2");
        hashBasedTable.put("a","bb","3");
        hashBasedTable.put("b","bb","5");
        System.out.println(hashBasedTable);
        //以行为纬度获取这一行的map
        System.out.println(hashBasedTable.row("a").get("aa"));
        //以列为纬度获取这一列的map
        System.out.println(hashBasedTable.column("bb"));
    }
}
