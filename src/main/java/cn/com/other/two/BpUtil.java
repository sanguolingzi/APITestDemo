package cn.com.other.two;

import java.util.Arrays;

public class BpUtil {

    public static void handleMaxAndMin(double[][] trainInput,double[] trainOut,double[][] trainInputMinAndMax,double[] trainOutMaxAndMin){
        //定义二位数据 每一维是2个长度  记录最小值 最大值
        //trainInputMinAndMax = new double[trainInput[0].length][2];
        //trainOutMaxAndMin = new double[2];

        int inLength = trainInput.length;

        for(int i=0;i<inLength;i++){
            int inInnerLength = trainInput[i].length;
            for(int j=0;j<inInnerLength;j++){
                double t = trainInput[i][j];

                if( i == 0){
                    trainInputMinAndMax[j][0] = t;
                    trainInputMinAndMax[j][1] = t;
                }else{
                    if(t < trainInputMinAndMax[j][0]){
                        trainInputMinAndMax[j][0] = t;
                        continue;
                    }
                    if(t > trainInputMinAndMax[j][1]){
                        trainInputMinAndMax[j][1] = t;
                    }
                }


            }
        }

        inLength = trainOut.length;
        for(int i=0;i<inLength;i++){
            double t = trainOut[i];
            if(t < trainOutMaxAndMin[0] || t == 0){
                trainOutMaxAndMin[0] = t;
            }

            if(t > trainOutMaxAndMin[1]){
                trainOutMaxAndMin[1] = t;
            }
        }
    }

    public static double[][]  handleLineIn(double[][] trainInput,double[][] trainInputMinAndMax){
            double[][] newDoubleArr = new double[trainInput.length][];
            int length = trainInput.length;
            for(int i=0;i<length;i++){
                newDoubleArr[i] = handleLineIn(trainInput[i],trainInputMinAndMax);
            }
            return newDoubleArr;
    }

    public static double[] handleLineIn(double[] value,double[][]trainInputMinAndMax){
        int length = value.length;
        double[] newValue = new double[length];
        for(int i=0;i<length;i++){
            newValue[i] = getValue(value[i],trainInputMinAndMax[i][0],trainInputMinAndMax[i][1]);
        }
        System.out.println(Arrays.toString(newValue));
        return newValue;
    }


    public static double[]  handleLineOut(double[] trainOut,double[] trainOutMaxAndMin){
        int length = trainOut.length;
        double[] newValue = new double[length];
        for(int i=0;i<length;i++){
            newValue[i] = getValue(trainOut[i],trainOutMaxAndMin[0],trainOutMaxAndMin[1]);
        }
        return newValue;
    }

    public static double getValue(double ori,double min,double max){
        return (ori-min)/(max-min);
    }
}
