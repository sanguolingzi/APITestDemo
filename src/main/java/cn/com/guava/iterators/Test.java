package cn.com.guava.iterators;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.ObjectArrays;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

/**
 * 优化集合迭代器的一些相关操作
 */
public class Test {
    public static void main(String[] args) throws Exception{
/*
        List list = Lists.newArrayList("a","b","c","d","f","g","a");
        System.out.println(Iterators.get(list.iterator(),2));
        Iterators.removeIf(list.iterator(), new Predicate<String>() {
            public boolean apply(@Nullable String s) {
                return Objects.equal(s,"a");
            }
        });
        //Iterators.
        System.out.println(list);
       // System.out.println(Iterators.getOnlyElement(list.iterator()));
       */
    }

}
