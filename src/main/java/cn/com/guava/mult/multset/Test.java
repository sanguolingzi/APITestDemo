package cn.com.guava.mult.multset;

import com.google.common.collect.*;

public class Test {
    public static void main(String[] a) throws Exception {
        HashMultiset set = HashMultiset.create();
        set.add("a");
        set.add("a");
        set.add("a");
        set.add("a");
        System.out.println(set.count("a"));

        //减少或设置元素的计数。setCount(elem, 0)等同于移除所有elem。
        set.setCount("a",0);
        System.out.println(set);
        LinkedHashMultiset linkedHashMultiset = LinkedHashMultiset.create();
        linkedHashMultiset.add(Lists.newArrayList(new String[]{"a","b"}));
        linkedHashMultiset.add("1");
        linkedHashMultiset.add("1");

        System.out.println(linkedHashMultiset);

        TreeMultiset treeMultiset = TreeMultiset.create();
        treeMultiset.add("a",12);
        treeMultiset.add("c",5);
        System.out.println(treeMultiset);

        System.out.println(treeMultiset.subMultiset("a", BoundType.OPEN,"c", BoundType.CLOSED));
    }
}
