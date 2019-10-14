package cn.com.other.one;

/**
 * 封装激活函数及激活函数导函数的函数工具类。
 * @author JiBin
 * @date 2018/6/9 1:20
 */

public class Function {

    /**
     * 激活函数,单个处理。
     * @param x 激活函数的参数值。
     * @return 激活函数运算后的函数值。
     */
    public static double sigmoid(double x){
        double result = 0.0;
        result = 1d/(1d+Math.exp(-x));
        return result;
    }

    /**
     * 激活函数，批处理,一维矩阵。
     * @param x 需要传入激活函数进行运算的一维参数矩阵。
     * @return 激活函数运算后的一维函数值矩阵。
     */
    public static double[] sigmoid(double[] x){
        double[] result = new double[x.length];
        for (int i=0;i<x.length;i++){
            result[i] = sigmoid(x[i]);
        }
        return result;
    }

    /**
     * 激活函数，批处理,二维矩阵。
     * @param x 需要传入激活函数进行运算的二维参数矩阵。
     * @return 激活函数运算后的二维函数值矩阵。
     */
    public static double[][] sigmoid(double[][] x){
        double[][] result = new double[x.length][x[0].length];
        for (int i=0;i<x.length;i++){
            for (int j=0;j<x[0].length;j++){
                result[i][j] = sigmoid(x[i][j]);
            }
        }
        return result;
    }

    /**
     * 激活函数导函数，单个处理。
     * @param x 激活函数导函数的参数值。
     * @return 激活函数导函数运算后的函数值。
     */
    public static double deSigmoid(double x){
        double result = 0.0;
        result = (Math.exp(x))/(Math.pow(Math.exp(x)+1, 2));
        return result;
    }

    /**
     * 激活函数导函数，批处理，一维矩阵。
     * @param x 需要传入激活函数导函数进行运算的一维参数矩阵。
     * @return 激活函数导函数运算后的一维函数值矩阵。
     */
    public static double[] deSigmoid(double[] x){
        double[] result = new double[x.length];
        for (int i=0;i<x.length;i++){
            result[i] = deSigmoid(x[i]);
        }
        return result;
    }

    /**
     * 激活函数导函数，批处理，二维矩阵。
     * @param x 需要传入激活函数导函数进行运算的二维参数矩阵。
     * @return 激活函数导函数运算后的二维函数值矩阵。
     */
    public static double[][] deSigmoid(double[][] x){
        double[][] result = new double[x.length][x[0].length];
        for (int i=0;i<x.length;i++){
            for (int j=0;j<x[0].length;j++){
                result[i][j] = deSigmoid(x[i][j]);
            }
        }
        return result;
    }
}
