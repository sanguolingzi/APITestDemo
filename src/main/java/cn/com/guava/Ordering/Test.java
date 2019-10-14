package cn.com.guava.Ordering;

import com.google.common.base.Strings;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import org.checkerframework.checker.nullness.qual.Nullable;

public class Test {


    public static void main(String[] rags) throws Exception{
        Ordering<String> ordering = new Ordering<String>() {
            @Override
            public int compare(@Nullable String s, @Nullable String t1) {
                return Ints.compare(s.length(),t1.length());
            }
        };
    }
}
