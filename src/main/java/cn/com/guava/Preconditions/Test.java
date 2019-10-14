package cn.com.guava.Preconditions;

import com.google.common.base.Preconditions;

public class Test {
    public static void main(String[] rags) throws Exception{

        //Preconditions.checkArgument(false,"自定义的错误消息!");

        //Preconditions.checkNotNull(null,"自定义消息!");

        System.out.println(Preconditions.checkElementIndex(0,2));
    }
}
