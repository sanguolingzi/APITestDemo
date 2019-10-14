package cn.com.other.one;

/**
 * 封装有关矩阵运算的操作工具类。
 * @author JiBin
 * @date 2018/6/9 1:32
 */
public class Matrix {

    /**
     * 两个二维矩阵相乘。
     * @param matrix1 第一个二维矩阵。
     * @param matrix2 第二个二维矩阵。
     * @return 两个二维矩阵相乘的结果。
     * @throws MatrixException
     */
    public static double[][] matrixMultiple(double[][] matrix1, double[][] matrix2) throws MatrixException {
        int matrix1Row = matrix1.length;
        int matrix1Col = matrix1[0].length;
        int matrix2Row = matrix2.length;
        int matrix2Col = matrix2[0].length;
        if(matrix1Col!=matrix2Row){
            throw new MatrixException("matrix1的列数与matrix2的行数不相等，因此两矩阵不能相乘！");
        }
        double[][] result = new double[matrix1Row][matrix2Col];
        for(int i=0;i<matrix1Row;i++){
            for(int j=0;j<matrix2Col;j++){
                double temp = 0;
                for(int k=0;k<matrix1Col;k++){
                    temp+= matrix1[i][k]*matrix2[k][j];
                }
                result[i][j] = temp;
            }
        }
        return result;
    }

    /**
     * 二维矩阵与数相乘，二维矩阵中的每个元素与数相乘为对应位置元素值。
     * @param matrix 二维矩阵。
     * @param lr 数。
     * @return 二维矩阵与数相乘的结果。
     */
    public static double[][] matrixMultiple(double[][] matrix,double lr){
        int matrixRow = matrix.length;
        int matrixCol = matrix[0].length;
        double[][] result = new double[matrixRow][matrixCol];
        for(int i=0;i<matrixRow;i++){
            for(int j=0;j<matrixCol;j++){
                result[i][j] = matrix[i][j]*lr;
            }
        }
        return result;
    }

    /**
     * 两个一维矩阵按位相乘。
     * @param matrix1 第一个一维矩阵。
     * @param matrix2 第二个一维矩阵。
     * @return 相乘之后的一维结果矩阵。
     * @throws MatrixException
     */
    public static double[] matrixMultiple(double[] matrix1, double[] matrix2)throws MatrixException{
        if (matrix1.length != matrix2.length){
            throw new MatrixException("两个一维矩阵长度不相等，因此不能按位相乘！");
        }
        double[] result = new double[matrix1.length];
        for (int i=0;i<matrix1.length;i++){
            result[i] = matrix1[i] * matrix2[i];
        }
        return result;
    }

    /**
     * 二维矩阵与一维矩阵相乘。
     * @param matrix1 二维矩阵(m*n)。
     * @param matrix2 一维矩阵(n)，可以看成(n*1)的二维矩阵。
     * @return 只有一列的二维矩阵(m*1)。
     * @throws MatrixException
     */
    public static double[][] matrixMultiple(double[][] matrix1, double[] matrix2)throws MatrixException{
        if (matrix1[0].length != matrix2.length){
            throw new MatrixException("二维矩阵的列数不等于一维矩阵的行数，因此不能相乘！");
        }
        double[][] result = new double[matrix1.length][1];
        for (int i=0;i<matrix1.length;i++){
            double temp = 0.0;
            for (int j=0;j<matrix2.length;j++){
                temp = temp + matrix1[i][j] * matrix2[j];
            }
            result[i][0] = temp;
        }
        return result;
    }

    /**
     * 两个二维矩阵按位相乘，相乘的结果放在新矩阵的对应位置。
     * @param matrix1 第一个二维矩阵(m*n)。
     * @param matrix2 第二个二维矩阵(m*n)。
     * @param flag 标识，传值为true/false/null都行，为区别矩阵相乘函数。
     * @return 按位相乘后的二维结果矩阵(m*n)。
     * @throws MatrixException
     */
    public static double[][] matrixMultiple(double[][] matrix1, double[][] matrix2, boolean flag)throws MatrixException{
        int matrix1Row = matrix1.length;
        int matrix1Col = matrix1[0].length;
        int matrix2Row = matrix2.length;
        int matrix2Col = matrix2[0].length;
        if (matrix1Row != matrix2Row || matrix1Col != matrix2Col){
            throw new MatrixException("两个二维矩阵的行或列数不相等，因此不能按位相乘！");
        }
        double[][] result = new double[matrix1Row][matrix1Col];
        for (int i=0;i<matrix1Row;i++){
            for (int j=0;j<matrix1Col;j++){
                result[i][j] = matrix1[i][j] * matrix2[i][j];
            }
        }
        return result;
    }

    /**
     * 两个二维矩阵相加减，对应位置元素相加减。
     * @param matrix1 第一个二维矩阵(m*n)。
     * @param matrix2 第二个二维矩阵(m*n)。
     * @param flag 传-1表示相减，其他值表示相加。
     * @return 相加减的二维结果矩阵。
     * @throws MatrixException
     */
    public static double[][] matrixAdd(double[][] matrix1, double[][] matrix2, double flag)throws MatrixException{
        int matrix1Row = matrix1.length;
        int matrix1Col = matrix1[0].length;
        int matrix2Row = matrix2.length;
        int matrix2Col = matrix2[0].length;
        if (matrix1Row != matrix2Row || matrix1Col != matrix2Col){
            throw new MatrixException("matrix1的行列数与matrix2的行列数不相等，因此两矩阵不能相加减！");
        }
        if (flag == -1)
            matrix2 = matrixMultiple(matrix2, flag);
        double[][] result = new double[matrix1Row][matrix1Col];
        for (int i=0;i<matrix1Row;i++){
            for (int j=0;j<matrix1Col;j++){
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    /**
     * 两个一维矩阵相减，对应位置元素相减的结果放在新矩阵的对应位置。
     * @param matrix1 第一个一维矩阵
     * @param matrix2 第二个一维矩阵
     * @return 两个一维矩阵相减的一维结果矩阵
     * @throws MatrixException
     */
    public static double[] matrixSub(double[] matrix1, double[] matrix2)throws MatrixException{
        int matrix1Length = matrix1.length;
        int matrix2Length = matrix2.length;
        if (matrix1Length != matrix2Length){
            throw new MatrixException("matrix1的长度与matrix2的长度不相等，因此不能相加减！");
        }
        double[] result = new double[matrix1Length];
        for (int i=0;i<matrix1Length;i++){
            result[i] = matrix1[i] - matrix2[i];
        }
        return result;
    }

    /**
     * 二维矩阵转置，第i行变成第i列，第j列变成第j行。
     * @param matrix 需要被转置的二维矩阵。
     * @return 转置后的二维矩阵。
     */
    public static double[][] matrixTranspose(double[][] matrix){
        int matrixRow = matrix.length;
        int matrixCol = matrix[0].length;
        double[][] result = new double[matrixCol][matrixRow];
        for (int i=0;i<matrixRow;i++){
            for (int j=0;j<matrixCol;j++){
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    /**
     * 求一维矩阵中元素的平方和。
     * @param matrix 一维矩阵。
     * @return 一维矩阵中所有元素的平方和。
     */
    public static double quadraticSum(double[] matrix){
        double result = 0.0;
        for (int i=0;i<matrix.length;i++){
            result = result + Math.pow(matrix[i], 2);
        }
        return result;
    }

    /**
     * 将列数为1的二维矩阵转化成一维行矩阵。
     * @param matrix 二维矩阵。
     * @return 转化后的一维矩阵。
     * @throws MatrixException
     */
    public static double[] change(double[][] matrix)throws MatrixException {
        if (matrix[0].length != 1){
            throw new MatrixException("该二维矩阵的列数不等于1，因此不能转换成一维矩阵！");
        }
        double[] result = new double[matrix.length];
        for (int i=0;i<matrix.length;i++){
            result[i] = matrix[i][0];
        }
        return result;
    }

    /**
     * 一维行矩阵转化成只有一列二维矩阵。
     * @param matrix 一维行矩阵
     * @return 只有一列的二维矩阵。
     */
    public static double[][] change(double[] matrix){
        double[][] result = new double[matrix.length][1];
        for (int i=0;i<matrix.length;i++){
            result[i][0] = matrix[i];
        }
        return result;
    }
}
