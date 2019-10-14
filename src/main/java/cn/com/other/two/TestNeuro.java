package cn.com.other.two;

import java.util.Arrays;

public class TestNeuro {
    private int INPUT_DIM=1;
    private int HIDDEN_DIM=20;
    private double LEARNING_RATE=0.05;
    //double [][] input_hidden_weights=new double[INPUT_DIM][HIDDEN_DIM];
    //double [] hidden_output_weights=new double[HIDDEN_DIM];
    //double[] hidden_thresholds=new double[HIDDEN_DIM];
    double [][] input_hidden_weights= null;
    double [] hidden_output_weights = null;
    double[] hidden_thresholds= null;
    double output_threshold;

    public static void main(String[]args){
        TestNeuro neuro=new TestNeuro(7,5);
        neuro.initialize();
        double[][] in = {
                {19.64,22724.5,7.57,3.0,14.54,151.2,1.98},
                {18.76,24724.5,7.87,2.6,17.43,161.2,1.87},
                {16.55,21223.5,6.87,2.55,15.3,163.2,1.78}
                //{2.1,18456,91,0.2,0.6,6.2},
                //{2.4,18555,76,0.1,0.8,5.8},
                //{1.9,17877,88,0.5,0.9,6.1},
                //{1.8,18122,51,0.4,0.4,5.7},
                //{1.5,17999,87,0.4,0.3,4.2}
        };
        double[] out = {0.312,0.332,0.342};

        double[][] trainInputMinAndMax = new double[in[0].length][2];;
        double[] trainOutMaxAndMin =  new double[2];;
        BpUtil.handleMaxAndMin(in,out,trainInputMinAndMax,trainOutMaxAndMin);
        double[][] trainInput = BpUtil.handleLineIn(in,trainInputMinAndMax);
        double[] trainOut = BpUtil.handleLineOut(out,trainOutMaxAndMin);
        System.out.println(Arrays.toString(trainOut));
        for(int i=0;i<in.length;i++){
            for(int j=0;j<10000;j++){
            //double[] input=new double[1];
            //input[0]=Math.random();
            //double expectedOutput=input[0]*input[0];
            //System.out.println("input : "+input[0]+"\t\texpectedOutput : "+expectedOutput);
            //System.out.println("predict before training : "+neuro.predict(input));
            neuro.trainOnce(trainInput[i], trainOut[i]);
            //System.out.println("predict after training : "+neuro.predict(input));
            //in.next();
            }
        }
        //double[] train = {16.77,1.546767,10.65,2,14.67,161.2,1.21};
        //0.3999998775794364
        //0.3999965701200472
        //0.3999999999999821
        double[] train = {1,2,3,4,5,6,7};
        double[] trainInHandle = BpUtil.handleLineIn(train,trainInputMinAndMax);
        System.out.println(Arrays.toString(trainInHandle));
        double result = neuro.predict(trainInHandle);
        System.out.println("result:"+result);
        System.out.println("result:"+result*(trainOutMaxAndMin[1]-trainOutMaxAndMin[0])+trainOutMaxAndMin[0]);
    }

    public TestNeuro(int input_dimension,int hidden_dimension){
        this.INPUT_DIM=input_dimension;
        this.HIDDEN_DIM=hidden_dimension;
        input_hidden_weights = new double[INPUT_DIM][HIDDEN_DIM];
        hidden_output_weights=new double[HIDDEN_DIM];
        hidden_thresholds=new double[HIDDEN_DIM];
        this.initialize();
    }


    /**
     * 打印出本神经元网络各层之间的连接权重，以及各个神经元上的阈值的信息。
     */
    void print(){
        System.out.println("隐含层阈值：");
        for(int i=0;i<HIDDEN_DIM;i++){
            System.out.print(hidden_thresholds[i]+" ");
        }System.out.println();
        System.out.println("输出层阈值：");
        System.out.println(output_threshold);

        System.out.println("连接权重：*********************");
        System.out.println("输入层与隐含层的连接");
        for(int i=0;i<INPUT_DIM;i++){
            for(int j=0;j<HIDDEN_DIM;j++){
                System.out.print(input_hidden_weights[i][j]+" ");
            }System.out.println();
        }
        System.out.println("隐含层到输出层的连接");
        for(int i=0;i<HIDDEN_DIM;i++){
            System.out.print(hidden_output_weights[i]+" ");
        }System.out.println();
        System.out.println("*********************************");
    }

    /**
     * 初始化，对所有的权值产生一个(0,1)之间的随机double型值
     */
    void initialize(){

        //输入层到隐含层的连接权重
        for(int i=0;i<INPUT_DIM;i++){
            for(int j=0;j<HIDDEN_DIM;j++){
                input_hidden_weights[i][j]=Math.random();
            }
        }
        //隐含层到输出层的连接权重
        for(int i=0;i<HIDDEN_DIM;i++){
            hidden_output_weights[i]=Math.random();
        }
        //隐含层的阈值
        for(int i=0;i<HIDDEN_DIM;i++){
            hidden_thresholds[i]=Math.random();
        }
        //输出层的阈值
        output_threshold=Math.random();
    }

    /**
     * 激励函数
     * @param x
     * @return
     */
    double function(double x){
        return 1/(1+Math.pow(Math.E, -x));
    }

    /**
     * 给定一个输入，进行预测
     * @param input
     * @return
     */
    double predict(double[]input){
        double[] hiddenValues=new double[HIDDEN_DIM];
        for(int i=0;i<hiddenValues.length;i++){
            double sum=0;
            for(int j=0;j<input.length;j++){
                sum+=input[j]*input_hidden_weights[j][i];
            }
            sum+=hidden_thresholds[i];//再加上本神经元的阈值
            hiddenValues[i]=function(sum);
        }


        double sum=0;
        for(int i=0;i<HIDDEN_DIM;i++){
            sum+=hiddenValues[i]*hidden_output_weights[i];
        }
        sum+=output_threshold;//输出层神经元的阈值
        return function(sum);
    }

    /**
     * 进行一次训练
     * @param input
     * @param expectedOutput
     */
    void trainOnce(double[] input, double expectedOutput){
        double[] hiddenValues=new double[HIDDEN_DIM];
        double[] hiddenParams=new double[HIDDEN_DIM];

        for(int i=0;i<hiddenValues.length;i++){
            double sum=0;
            for(int j=0;j<input.length;j++){
                sum+=input[j]*input_hidden_weights[j][i];
            }
            sum+=hidden_thresholds[i];//
            hiddenValues[i]=function(sum);
            hiddenParams[i]=sum;
        }

        double sum=0;
        for(int i=0;i<HIDDEN_DIM;i++){
            sum+=hiddenValues[i]*hidden_output_weights[i];
        }
        sum+=output_threshold;//
        double outputValue=function(sum);
        double outputParam=sum;
        //System.out.println("实际输出");

		/*
		 * 调整权值和阈值
		 */

        for(int i=0;i<input.length;i++){
            double factor=(expectedOutput-outputValue)*outputValue*(1-outputValue)*LEARNING_RATE*input[i];
            for(int j=0;j<HIDDEN_DIM;j++){
                double delta=factor*hidden_output_weights[j]*hiddenValues[j]*(1-hiddenValues[j]);
                //System.out.println("输入层到隐含层连接的权重调整：delta = "+delta+"\t\t weight = "+input_hidden_weights[i][j]);
                input_hidden_weights[i][j]+=delta;
            }
        }
        double factor=(expectedOutput-outputValue)*outputValue*(1-outputValue)*LEARNING_RATE;
        for(int i=0;i<hidden_thresholds.length;i++){
            double delta=factor*hidden_output_weights[i]*hiddenValues[i]*(1-hiddenValues[i]);
            hidden_thresholds[i]+=delta;
        }

        //System.out.println("hidden_output_weights : "+hidden_output_weights.length);
        for(int i=0;i<hidden_output_weights.length;i++){
            //w+=(exp-act)*df/dw
            //df/dw=x(1-x)*hiddenj
            double delta=factor*hiddenValues[i];
            //System.out.println("隐含层到输出层连接的权值调整：delta = "+delta+"\t\t weight = "+hidden_output_weights[i]);
            hidden_output_weights[i]+=delta;

        }
        double delta=(expectedOutput-outputValue)*outputValue*(1-outputValue)*LEARNING_RATE;
        output_threshold+=delta;
        if(Math.abs(outputValue-expectedOutput)>0.1){
            //System.out.println(input[0]+"\t\t"+outputValue+"\t\t"+expectedOutput);
        }
    }
}
