package cn.com.wxpay;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) throws Exception{
        //String s = "劺\u000F?K";
        //System.out.println(new String(s.getBytes("utf-8"),"utf-8"));


         ObjectMapper mapper = new ObjectMapper();
         double[] array = mapper.readValue("[0.2,0,2,0.1,1.3]", new TypeReference<double[]>() {  });
         System.out.println(Arrays.toString(array));

         System.out.println(mapper.writeValueAsString(array));


         NumberFormat nf = NumberFormat.getInstance();
         //设置保留多少位小数
         nf.setMaximumFractionDigits(8);
         // 取消科学计数法
         nf.setGroupingUsed(false);

         double s = 20.123;

         int divided = 10;
         double result = s/divided;
         System.out.println(result);
         while(result>1){
             divided*=10;
             result = s/divided;
         }
         System.out.println(nf.format(result));

        BigDecimal temp = BigDecimal.valueOf(1.8987778881231231312E12);
        System.out.println(temp.setScale(8,BigDecimal.ROUND_HALF_UP).doubleValue());

        System.out.println(nf.format(1.8987778881231231312E12));
        System.out.println(1d+Math.exp(-0.3));
       // 1d/(1d+Math.exp(-))
    }
}
