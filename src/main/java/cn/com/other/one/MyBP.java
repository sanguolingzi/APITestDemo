package cn.com.other.one;

/**
 * 模拟BP神经网络，3层结构（输入层、隐含层和输出层）。
 * @author JiBin
 * @date 2018/6/9 2:05
 */
public class MyBP {
        /**
         * 训练时的实际输出。
         */
        private double[] o;

        /**
         * 训练时的输入。
         */
        private double[][] trainInput;

        /**
         * 期望输出。
         */
        private double[] output;

        /**
         * 输入层与隐含层之间的连接权值矩阵。
         */
        private double[][] inputHiddenWeight;

        /**
         * 隐含层与输出层之间的连接权值矩阵。
         */
        private double[][] hiddenOutputWeight;

        /**
         * 前一次的训练误差平方和。
         */
        private double preErrorSum;

        /**
         * 当前训练误差平方和。
         */
        private double curErrorSum;

        /**
         * 当前训练误差。
         */
        private double[] curOutError;

        /**
         * 学习速率。
         */
        private double lr;

        /**
         * 带参构造器，初始化网络
         * @param trainInput 对网络进行训练学习时的输入。
         * @param output 训练输入对应的输出。
         * @param lr 学习速率。
         * @param hidden 隐含层神经元节点的个数。
         */
        public MyBP(double[][] trainInput, double[] output, double lr, int hidden) {
            this.trainInput = trainInput;
            this.output = output;
            this.lr = lr;
            //设置权值矩阵的行和列数
            this.inputHiddenWeight = new double[trainInput[0].length][hidden];
            this.hiddenOutputWeight = new double[hidden][1];
            //初始化前一次的训练误差平方和为0
            this.preErrorSum = 0.0;
            //初始化权值矩阵
            InitWeight();
        }

        /**
         * 初始化两个权值矩阵（输入--隐含权值矩阵和隐含--输出权值矩阵）。
         */
        private void InitWeight(){
            //初始化输入层与隐含层之间的连接权值矩阵，取值范围（-0.5,0.5]
            for (int i=0;i<inputHiddenWeight.length;i++){
                for (int j=0;j<inputHiddenWeight[0].length;j++){
                    inputHiddenWeight[i][j] = 0.5 - Math.random();
                }
            }
            //初始化隐含层与输出层之间的连接权值矩阵，取值范围（-0.5,0.5]
            for (int i=0;i<hiddenOutputWeight.length;i++){
                for (int j=0;j<hiddenOutputWeight[0].length;j++){
                    hiddenOutputWeight[i][j] = 0.5 - Math.random();
                }
            }
        }

        /**
         * 计算当前训练的输出误差及误差平方和。
         * @param curOutput 当前训练得到的训练输出。
         */
        private void calculateError(double[] curOutput){

            try {
                curOutError = Matrix.matrixSub(output,curOutput );
            } catch (MatrixException e) {
                e.printStackTrace();
            }
            curErrorSum = Matrix.quadraticSum(curOutError);
        }

        /**
         * 根据上一次训练的误差平方和与当前训练的误差平方和的关系调整学习速率。
         */
        private void updateLr(){
            if (curErrorSum > 1.04 * preErrorSum){
                lr = 0.7 * lr;
            }
            else if (curErrorSum < preErrorSum){
                lr = 1.05 * lr;
            }
            else {
                lr = lr;
            }
            preErrorSum = curErrorSum;
        }

        /**
         * 单次训练，对神经网络只训练一次。
         * @throws MatrixException
         */
        public void train()throws MatrixException{
            //隐含层的输入
            double[][] hiddenIn = Matrix.matrixMultiple(trainInput, inputHiddenWeight);
            //隐含层的输出
            double[][] hiddenOut = Function.sigmoid(hiddenIn);
            //输出层的输入
            double[] outIn = Matrix.change(Matrix.matrixMultiple(hiddenOut, hiddenOutputWeight));
            //输出层的输出
            double[] outOut = Function.sigmoid(outIn);
            o = outOut;
            //计算当前训练的误差（输出层的输出误差）
            calculateError(outOut);
            //修正学习速率
            updateLr();
            //输出层的输入误差
            double[] outInError = Matrix.matrixMultiple(Function.deSigmoid(outIn), curOutError);
            //隐含层与输出层之间的权值误差
            double[][] hiddenOutWeightError =Matrix.matrixMultiple(Matrix.matrixTranspose(hiddenOut), outInError);
            hiddenOutWeightError = Matrix.matrixMultiple(hiddenOutWeightError, lr);
            //调整隐含层与输出层之间的权值
            hiddenOutputWeight = Matrix.matrixAdd(hiddenOutputWeight, hiddenOutWeightError, 1.0);
            //隐含层的输出误差
            double[][] hiddenOutError = Matrix.matrixMultiple(Matrix.change(outInError), Matrix.matrixTranspose(hiddenOutputWeight));
            //隐含层的输入误差
            double[][] hiddenInError = Matrix.matrixMultiple(hiddenOutError, Function.deSigmoid(hiddenIn), true);
            //输入层与隐含层之间的权值误差
            double[][] inputHiddenWeightError = Matrix.matrixMultiple(Matrix.matrixTranspose(trainInput), hiddenInError);
            inputHiddenWeightError = Matrix.matrixMultiple(inputHiddenWeightError, lr);
            //调整输入层与隐含层之间的权值
            inputHiddenWeight = Matrix.matrixAdd(inputHiddenWeight, inputHiddenWeightError, 1.0);
        }

        /**
         * 多次训练，对神经网络训练所给的参数值次。
         * @param num 训练的次数。
         */
        public void train(int num)throws MatrixException{
            for (int i=1;i<=num;i++){
                train();
                if (i%100 == 0){
                    System.out.println("第"+i+"次训练error:"+curErrorSum);
                    System.out.println("实际输出：");
                    for (int j=0;j<o.length;j++){
                        System.out.print(o[j]+" ");
                    }
                    System.out.println();
                    System.out.println("------------------------------------------------------------");
                }
            }
        }

        /**
         * 神经网络训练学习完毕后，给定一个输入，对输出进行预测。
         * @param in 输入层神经元的输入信息。
         * @param out 该输入对应的期望输出。
         * @throws MatrixException
         */
        public void forecase(double[][] in, double[] out)throws MatrixException{
            //隐含层的输入
            double[][] hiddenIn = Matrix.matrixMultiple(in, inputHiddenWeight);
            //隐含层的输出
            double[][] hiddenOut = Function.sigmoid(hiddenIn);
            //输出层的输入
            double[] outIn = Matrix.change(Matrix.matrixMultiple(hiddenOut, hiddenOutputWeight));
            //输出层的输出
            double[] outOut = Function.sigmoid(outIn);
            System.out.println("预测输出：");
            for (int i=0;i<outOut.length;i++){
                System.out.print(outOut[i]+"    ");
            }
            System.out.println();
            System.out.println("期望输出：");
            for (int i=0;i<out.length;i++){
                System.out.print(out[i]+"    ");
            }
        }
}
