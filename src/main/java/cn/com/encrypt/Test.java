package cn.com.encrypt;

import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import sun.security.provider.MD5;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) throws Exception{
        Map<String,Object> params = Maps.newHashMap();
        params.put("appid","12345678");
        params.put("timestamp","2016-01-01 12:00:00");
        params.put("format","json");
        params.put("sign_method","md5");
        params.put("id","11223344");
        SortedMap<String, String> sortedMap = new TreeMap(params);

        System.out.println("1212:"+DigestUtils.md5Hex("罗婧媛abcd1234"));
        //ASCII排序
        Iterator var6 = sortedMap.keySet().iterator();
        StringBuilder toSign = new StringBuilder();
        while(var6.hasNext()) {
            String key = (String)var6.next();
            String value = (String)params.get(key);
            toSign.append(key).append(value);
        }
        System.out.println(toSign.toString());
        String readyToMd5 = "secret"+toSign.toString()+"secret";
        System.out.println(DigestUtils.md5Hex(readyToMd5));




        //hmac_md5 有问题 目前无法执行
        //还原密钥
        SecretKey restoreSecretKey = new SecretKeySpec("secret".getBytes(), "secret");
        //实例化Mac
        Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
        //初始化MAC
        mac.init(restoreSecretKey);
        byte[] bytes = mac.doFinal(toSign.toString().getBytes());
        String encodedMsg = Hex.encodeHexString(bytes);
        System.out.println(encodedMsg);


    }
}
