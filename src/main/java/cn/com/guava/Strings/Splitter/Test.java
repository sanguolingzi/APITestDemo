package cn.com.guava.Strings.Splitter;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] rags) throws Exception{
        Iterable<String> inObjectCodeArr= Splitter.on("@")
                .trimResults()
                .split("a@b@c");
        System.out.println(inObjectCodeArr);

        inObjectCodeArr= Splitter.on("@")
                .trimResults()
                .splitToList("a@b@c");


        //二次拆分
        String toSplitString = "a=b;c=d,e=f";
        Map<String,String> kvs = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator('=').split(toSplitString);
        for (Map.Entry<String,String> entry : kvs.entrySet()) {
                 System.out.println(String.format("%s=%s", entry.getKey(),entry.getValue()));
        }

        //二次合并
        Map<String,String> map = new HashMap<String,String>();
        map.put("a", "b");
        map.put("c", "d");
        String mapJoinResult = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(mapJoinResult);


        System.out.println(CaseFormat.valueOf("a"));

    }
}
