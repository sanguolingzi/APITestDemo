package cn.com.guava.Math;

import com.google.common.math.IntMath;

import java.math.RoundingMode;

public class Test {

    public static void main(String[] rags) throws Exception{
        //最大公约数
        System.out.println(IntMath.gcd(3,6));

        //取模
        System.out.println(IntMath.mod(3,6));
        //取幂
        System.out.println(IntMath.pow(3,2));
        //是否2的幂
        System.out.println(IntMath.isPowerOfTwo(8));
        //阶乘
        System.out.println(IntMath.factorial(3));
        //二项式系数
        //System.out.println(IntMath.binomial(2,4));

        //除法
        System.out.println(IntMath.divide(3,6, RoundingMode.HALF_UP));
        //2为底的对数
        System.out.println(IntMath.log2(3,RoundingMode.HALF_UP));

        //10为底的对数
        System.out.println(IntMath.log10(3,RoundingMode.HALF_UP));
        //平方根
        System.out.println(IntMath.sqrt(9,RoundingMode.HALF_UP));


    }
}
