package cn.com.other.one;

/**
 * 对神经网络进行运行测试的测试类。
 * @author JiBin
 * @date 2018/6/9 2:23
 */
public class TestBp {

    /**
     * 主函数，java应用程序的入口。
     * @param args JVM调用时传入的相关参数。
     */
    public static void main(String[] args) {
        double[][] in = {
                {19.64,2.47245,7.87,3.0,14.54,161.2,1.98},
                {5.76,2.47245,7.87,2.6,17.43,161.2,0.87}
                //{2.1,18456,91,0.2,0.6,6.2},
                //{2.4,18555,76,0.1,0.8,5.8},
                //{1.9,17877,88,0.5,0.9,6.1},
                //{1.8,18122,51,0.4,0.4,5.7},
                //{1.5,17999,87,0.4,0.3,4.2}
        };
        double[] out = {0.3,0.4};
        //double[] out = {0.22};
        BpNet myBP = new BpNet(7,1, 0.11, 6);
        /*
        double[][] in = {
                {1000,10,10}, {300,7,9}, {100,2,3},
                {600,4,2}, {400,0,6},{100,4,7},{500,1,3}};
        double[] out = {0.90,0.69,0.22,0.28,0.34,0.48,0.23};
        MyBP myBP = new MyBP(in, out, 0.07, 6);
        */
        try {
            myBP.train(in,out,2000);
        } catch (MatrixException e) {
            e.printStackTrace();
        }

        double[][] testIn = {
                {1000,2000,3000,4000,5000,6000,7000},
        };
        double[] testOut = {0.87};
        /*
        double[][] testIn = {
                {2,5,8}
        };
        double[] testOut = {0.57};*/
        try {
            myBP.forecase(testIn, testOut);
            System.out.println("");
            System.out.println(myBP.getLr());
        } catch (MatrixException e) {
            e.printStackTrace();
        }
    }
}