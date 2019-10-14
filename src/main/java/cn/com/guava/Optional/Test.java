package cn.com.guava.Optional;

import com.google.common.base.Optional;

public class Test {
    public static void main(String[] a) throws Exception{

        //直接报错
        //Optional optional = Optional.of(null);

        //不会直接报错
        Optional optional = Optional.fromNullable(null);

        System.out.println(optional.isPresent());

        //null 对象调用 直接报错
        //System.out.println(optional.get());
        System.out.println(optional.orNull());

        System.out.println(optional.asSet());
    }
}
