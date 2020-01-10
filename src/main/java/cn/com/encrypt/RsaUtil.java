package cn.com.encrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA 算法工具类
 */
public class RsaUtil {

    //汇电提供公钥用来解密数据
    public static String pubKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtFOn21JbnZY4XRhS61T9HGQEWvievbPN\n" +
            "tuk/2BUGEPxfARTB0fl6Tu0g+Tc1PhbNCoZoPkj9TLaO2VTs4O0+tjK1VjCnlYCuCD2CSX8wPwDI\n" +
            "wBw0Vq9DmRw5D/2t07Xny3NKKJehrH5/cIcTKJPv8wc+mvOr9aXGytGGhwoRg6RUOpx7Lxz1Dl6y\n" +
            "2r+OjtwYHZ4f7pwPa9IYdPLPYMI/0zVftB+/tcI8sgPUzktz4JxLGHuOcjojkOEzu6p1egF7uiya\n" +
            "CSI4ByDDMYarSAL1zwyw8ImE+DjLkcsfCWfqDexhqPHas0pq8aQIi42Ki1pcf7cZPk9zBCfnR/QL\n" +
            "u4AuDwIDAQAB";

    //测试用 私钥 用来加密数据
    public static String t_priKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMjDB42+CTEvyacimUxzy9O1sjrR\n" +
            "mLeNWgKtK9CSOKX0gPjjdEBQfvN1sFgufV2Yp/AuoVADZCBIUhAcj+VHpIaztBpy+PoPc1Q6KcAT\n" +
            "WQsECD6z506/u0PQCV2yNNKP6XEUtez7AzGa0IQN0zY7Kz+YLEOUq1atM0RmCHJHun7ZAgMBAAEC\n" +
            "gYEArjorFQSF36zUOPraxTF9A1ccvNTmzuyZ39a/PnuXM9a0Esr6OVJiShCpoG2AGLkBjXw6PEEl\n" +
            "JKr+pMLU0irRiRcPZRmCNgFpB5iIpMSqWzwx91eJP2igh25/l/GWsjOe6dyuXWAHX06wrgrXfXcD\n" +
            "CtPiinC+EQqsCaZZyDrV8j0CQQDs3XvLBGOj66P0s5ZnQsZ/fwsT40aYTWGJqzHUiN8TahS3ubDd\n" +
            "MhBSqRA5Ilcp4p5jWj2wzZ6l7Bv8Y7MUlKUfAkEA2ProKfkHIRc4NgNB4wEbwf44BBBC90gAm49e\n" +
            "8YzXKNN4fOfz3z+O9t+SNNYjmn4wYvkXUSxJWPtG4iP46iOlBwJAOVbwldftKwxrN1yWJ08x8EFX\n" +
            "9CuLtmjdGM+Zk4LCxddjRoqsNw/chbYpmDso8JnMj+6Tio5sPmxm5fVYr73ALwJAJEy1knAK9UpB\n" +
            "KSgaWbPRxP6WuHdP/LabA2tU4uxBAp9+RMc5UVv8uuhkOVqp0irSRXyOnhHeo3hGP4btGZ6k+QJB\n" +
            "ANrCaFrWN6KxR7y59+eAMv4PEUearcA+JuLWewv2PgkcVdLDj8aFWHT5HvYR7UIduqYdQpvphvHJ\n" +
            "39q/0ifg52Q=";

    //测试用公钥 用来解密数据
    public static String t_pubKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIwweNvgkxL8mnIplMc8vTtbI60Zi3jVoCrSvQ\n" +
            "kjil9ID443RAUH7zdbBYLn1dmKfwLqFQA2QgSFIQHI/lR6SGs7Qacvj6D3NUOinAE1kLBAg+s+dO\n" +
            "v7tD0AldsjTSj+lxFLXs+wMxmtCEDdM2Oys/mCxDlKtWrTNEZghyR7p+2QIDAQAB";
    /**
     * 使用私钥加密数据
     * 用一个已打包成byte[]形式的私钥加密数据，即数字签名
     *
     * @param keyInByte 打包成byte[]的私钥
     * @param source    要签名的数据，一般应是数字摘要
     * @return 签名 byte[]
     */
    public static byte[] sign(byte[] keyInByte, byte[] source) {
        try {
            PKCS8EncodedKeySpec priv_spec = new PKCS8EncodedKeySpec(keyInByte);
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privKey = mykeyFactory.generatePrivate(priv_spec);
            Signature sig = Signature.getInstance("SHA1withRSA");
            sig.initSign(privKey);
            sig.update(source);
            return sig.sign();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证数字签名
     *
     * @param keyInByte 打包成byte[]形式的公钥
     * @param source    原文的数字摘要
     * @param sign      签名（对原文的数字摘要的签名）
     * @return 是否证实 boolean
     */
    public static boolean verify(byte[] keyInByte, byte[] source, byte[] sign) {
        try {
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            Signature sig = Signature.getInstance("SHA1withRSA");
            X509EncodedKeySpec pub_spec = new X509EncodedKeySpec(keyInByte);
            PublicKey pubKey = mykeyFactory.generatePublic(pub_spec);
            sig.initVerify(pubKey);
            sig.update(source);
            return sig.verify(sign);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 建立新的密钥对，返回打包的byte[]形式私钥和公钥
     *
     * @return 包含打包成byte[]形式的私钥和公钥的object[], 其中，object[0]为私钥byte[],object[1]为公钥byte[]
     */
    public static Object[] giveRSAKeyPairInByte() {
        KeyPair newKeyPair = creatmyKey();
        if (newKeyPair == null) {
            return null;
        }
        Object[] re = new Object[2];
        if (newKeyPair != null) {
            PrivateKey priv = newKeyPair.getPrivate();
            byte[] b_priv = priv.getEncoded();
            PublicKey pub = newKeyPair.getPublic();
            byte[] b_pub = pub.getEncoded();
            re[0] = b_priv;
            re[1] = b_pub;
            return re;
        }
        return null;
    }

    /**
     * 新建密钥对
     *
     * @return KeyPair对象
     */
    public static KeyPair creatmyKey() {
        KeyPair myPair;
        long mySeed;
        mySeed = System.currentTimeMillis();
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            random.setSeed(mySeed);
            keyGen.initialize(1024, random);
            myPair = keyGen.generateKeyPair();
        } catch (Exception e1) {
            return null;
        }
        return myPair;
    }

    /**
     * 使用RSA公钥加密数据
     *
     * @param pubKeyInByte 打包的byte[]形式公钥
     * @param data         要加密的数据
     * @return 加密数据
     */
    public static byte[] encryptByRSA(byte[] pubKeyInByte, byte[] data) {
        try {
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec pub_spec = new X509EncodedKeySpec(pubKeyInByte);
            PublicKey pubKey = mykeyFactory.generatePublic(pub_spec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 用RSA私钥解密
     *
     * @param privKeyInByte 私钥打包成byte[]形式
     * @param data          要解密的数据
     * @return 解密数据
     */
    public static byte[] decryptByRSA(byte[] privKeyInByte, byte[] data) {
        try {
            PKCS8EncodedKeySpec priv_spec = new PKCS8EncodedKeySpec(
                    privKeyInByte);
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privKey = mykeyFactory.generatePrivate(priv_spec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }

    }


    /**
     * 使用RSA私钥加密数据
     *
     * @param privKeyInByte 打包的byte[]形式私钥
     * @param data          要加密的数据
     * @return 加密数据
     */
    public static byte[] encryptByRSA1(byte[] privKeyInByte, byte[] data) {
        try {
            PKCS8EncodedKeySpec priv_spec = new PKCS8EncodedKeySpec(
                    privKeyInByte);
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privKey = mykeyFactory.generatePrivate(priv_spec);
            Cipher cipher = Cipher.getInstance(mykeyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 用RSA公钥解密
     *
     * @param pubKeyInByte 公钥打包成byte[]形式
     * @param data         要解密的数据
     * @return 解密数据
     */
    public static byte[] decryptByRSA1(byte[] pubKeyInByte, byte[] data) {
        try {
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec pub_spec = new X509EncodedKeySpec(pubKeyInByte);
            PublicKey pubKey = mykeyFactory.generatePublic(pub_spec);
            Cipher cipher = Cipher.getInstance(mykeyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, pubKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 计算字符串的SHA数字摘要，以byte[]形式返回
     */
    public static byte[] MdigestSHA(String source) {
        //byte[] nullreturn = { 0 };
        try {
            MessageDigest thisMD = MessageDigest.getInstance("SHA");
            byte[] digest = thisMD.digest(source.getBytes("UTF-8"));
            return digest;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        try {
//            //明文
//            String data = "你好！你好！你好！你好！你好！你好！你好！你好！你好！你好！你好！你好！你好！你好！你好！";
//            //私钥
//            String priKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC0U6fbUludljhdGFLrVP0cZARa\n" +
//                    "+J69s8226T/YFQYQ/F8BFMHR+XpO7SD5NzU+Fs0Khmg+SP1Mto7ZVOzg7T62MrVWMKeVgK4IPYJJ\n" +
//                    "fzA/AMjAHDRWr0OZHDkP/a3TtefLc0ool6Gsfn9whxMok+/zBz6a86v1pcbK0YaHChGDpFQ6nHsv\n" +
//                    "HPUOXrLav46O3Bgdnh/unA9r0hh08s9gwj/TNV+0H7+1wjyyA9TOS3PgnEsYe45yOiOQ4TO7qnV6\n" +
//                    "AXu6LJoJIjgHIMMxhqtIAvXPDLDwiYT4OMuRyx8JZ+oN7GGo8dqzSmrxpAiLjYqLWlx/txk+T3ME\n" +
//                    "J+dH9Au7gC4PAgMBAAECggEAc3qTJxfBxdQ6ehumlkAR60l9pIhMl+16UqVilk04Wk5TxMx3JrFe\n" +
//                    "Cwc+M3qQZReS7SePwDtbwvGx3ynMnGox29nzWKPF3i7OxEYnlkuWKPKmnKgzOEP6xSVWwY/R7p8y\n" +
//                    "No65Me+QwHctw3Es6rJ6WicMh4x+Vcx41s+BGB9xgdv6RhtggC7V1gWLTCwXUGZii+uVQ1ZhYUWX\n" +
//                    "LKoM8g38m8JB5rvQc4zoNprDzeQ980C5upBcrY+jEVlIiCQTW3mSHaireShFekLw+D4P0fhkzTK3\n" +
//                    "L678WMeRgHd2YPvYnJl0aiX/I4nZYHUvavjEW4X94qYvCHfBoDecY8NddLwFMQKBgQDYrjPl3sWa\n" +
//                    "wEYqontkkr281Rm1A0yYF61V6FyIU3Hcf/JZFbWNxlTi0eKYpK+KQxZn8Hh0h320vWYSrnXH5GbN\n" +
//                    "7y0+wSRPZ56lRsT+8dQhUukTTWDkPQ1TlGyKQRiSK9x90B+QHCAkvPqhHQOk/5gnzotyTZYRhFdp\n" +
//                    "2JURqs3tSQKBgQDVDKhe4edDiR1WNbImzDs6GtwjC+KcpJZPXDC4INulqVYI41G4xh4WHdEOukhK\n" +
//                    "zKnsuZsz8FuszK8IU6XhTox8vlkSHBsS4oq3UXnZLob3cF0Mb8qvgZIKTEHzL3elkQ6fBqiS0HKn\n" +
//                    "fqRdzKHS7YE8KQxcxQVScf2vc1yaSVd4lwKBgFn0uIcOXWtJt9erbR7r7OmgbvO/UuUxrzyjIwdV\n" +
//                    "3Ud1tt9VK/KYDFW+XRySAm6cO4kbRGbceUu39dB7rVo3cJa+XUJYXQK6OhrTrqVJMJBFI45n6XnX\n" +
//                    "/thcHlOngtPscbWp2fCyPIYEDukkaobttfWQwkPKAitzjfbbe8fB6YxBAoGARAiGu8Xn0+k1NRJw\n" +
//                    "kXMmdqedJJPnCZ6FYVJPPH1BpuUsVCpDLn4VnXpoCAv0ie5RVQbwSOW4YLX8LuyHTCuJMUyXpPDU\n" +
//                    "tHMjWObrwi7Zaqq3DAGm9+SofiFb7gx6aKSpxp6ZtkhaVKJcWpe+FV1Cp7Nz/yvrRyifmid8qshA\n" +
//                    "5oECgYAxFDx3BDKKHGe1aB1EuIng/3mos5Sw/mUK74ZvShp0vHCPyybPGGKAZmVcrz+BrDembT5H\n" +
//                    "ve0z1v4+OorP2rErBUQebYoApM/1zvndCAqqWS8ykdg0TS7RVXVueF3/xxCQSA74VNowHwkRQxTt\n" +
//                    "+6GYIZQ1j6dey2XFBG8cAuQhxw==";
//            //使用私钥对摘要进行加密 获得密文
//            byte[] signpri_pub = encryptByRSA1(new BASE64Decoder().decodeBuffer(priKey), data.getBytes());
//            System.out.println("私钥加密密文："+ URLEncoder.encode(new BASE64Encoder().encodeBuffer(signpri_pub),"UTF-8"));

            String key = "rOwRxzF2gqjjWXkamkrAXFe5nyWMzWkxjnc71I3A0qfixqDlk6VgeEwyiOLI%2FBFoatdLJD%2F895Kl%0D%0Ati6d0%2BTHn44pZ%2B9G3K0JiI%2BHznaw%2B72ICPPPa2KSmTmL1i1925qm2SP2FjvdEUNWjV2a9HgHeg9G%0D%0AoRhmCAmAk2W3iUE2BCQ4nGR0iKbU8u%2BRq4RifviqPZNYLDQGF3tkjbs7To6rr1xTN%2F8rW0kbXuMr%0D%0ADc0Sjr8qcb9th8d5%2B1Ewj1HAFFejwBgwP7u4F3Eu89eNcUk%2BFR4Z5Y7%2BnfZ4FCIOPGCd%2BtFLrgr6%0D%0AMMsy42ZeVorA7OyXhKZsHrIye%2BAfjzVfbUVIag%3D%3D%0D%0A";
            //公钥
            String pubKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtFOn21JbnZY4XRhS61T9HGQEWvievbPN\n" +
                    "tuk/2BUGEPxfARTB0fl6Tu0g+Tc1PhbNCoZoPkj9TLaO2VTs4O0+tjK1VjCnlYCuCD2CSX8wPwDI\n" +
                    "wBw0Vq9DmRw5D/2t07Xny3NKKJehrH5/cIcTKJPv8wc+mvOr9aXGytGGhwoRg6RUOpx7Lxz1Dl6y\n" +
                    "2r+OjtwYHZ4f7pwPa9IYdPLPYMI/0zVftB+/tcI8sgPUzktz4JxLGHuOcjojkOEzu6p1egF7uiya\n" +
                    "CSI4ByDDMYarSAL1zwyw8ImE+DjLkcsfCWfqDexhqPHas0pq8aQIi42Ki1pcf7cZPk9zBCfnR/QL\n" +
                    "u4AuDwIDAQAB";
            //使用公钥对密文进行解密 返回解密后的数据
            //byte[] newSourcepri_pub = decryptByRSA1(new BASE64Decoder().decodeBuffer(pubKey), new BASE64Decoder()
            //        .decodeBuffer(URLDecoder.decode(key,"UTF-8")));

            //System.out.println("公钥解密：" + new String(newSourcepri_pub, "UTF-8"));
            //testCreate();

            String data ="{\"account\":\"admin1\",\"mobile\":\"123123123\"}";
            System.out.println("---明文:"+data);
            String result = testEncryByPriKey(data);
            testDecryByPubKey(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 模拟私钥加密数据 使用t_priKey
     * @param data
     */
    public static String testEncryByPriKey(String data) throws Exception{
        byte[] signpri_pub = encryptByRSA1(new BASE64Decoder().decodeBuffer(t_priKey), data.getBytes());
        String result = URLEncoder.encode(new BASE64Encoder().encodeBuffer(signpri_pub),"UTF-8");
        System.out.println("私钥加密密文："+result);
        return result;
    }

    /**
     * 模拟公钥解密数据 使用t_pubKey
     * @param data
     */
    public static void testDecryByPubKey(String data) throws Exception{
        byte[] newSourcepri_pub = decryptByRSA1(new BASE64Decoder().decodeBuffer(t_pubKey), new BASE64Decoder()
                .decodeBuffer(URLDecoder.decode(data,"UTF-8")));
        System.out.println("公钥解密：" + new String(newSourcepri_pub, "UTF-8"));
    }


    /**
     * 模拟生成一对公钥私钥
     * @throws Exception
     */
    public static void testCreate() throws Exception{
        Object[] arr = giveRSAKeyPairInByte();
        System.out.println(java.util.Arrays.toString(arr));

        int i = 0;
        for(Object obj:arr){
            if(i == 0){
                System.out.println("私钥为:");
            }else{
                System.out.println("公钥为:");
            }
            i++;
            System.out.println(new BASE64Encoder().encodeBuffer((byte[]) obj));
        }
    }
}
