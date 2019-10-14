package cn.com.guava.Strings;

import com.google.common.base.Strings;

public class Tesst {
    public static void main(String[] rags)throws Exception{
        //padEnd 在字符串末尾添加minLength - "123".length 个 0
        System.out.println(Strings.padEnd("123",5,'0'));

        //padEnd 在字符串开头添加minLength - "123".length 个 0
        System.out.println(Strings.padStart("123",5,'0'));

        //获取2个字符串共同的结尾
        System.out.println(Strings.commonSuffix("abc","bbc"));

        //获取2个字符串共同的开头
        System.out.println(Strings.commonPrefix("abc","bbc"));
    }
}
