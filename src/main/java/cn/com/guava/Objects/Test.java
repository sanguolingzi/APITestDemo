package cn.com.guava.Objects;


import com.google.common.base.Objects;

public class Test {
    public static void main(String[] a) throws Exception {

        System.out.println(Objects.equal(""," "));
        System.out.println(Objects.hashCode("a","b"));
    }
}
