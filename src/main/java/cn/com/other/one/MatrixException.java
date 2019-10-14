package cn.com.other.one;

/**
 * 自定义异常类
 * @author JiBin
 * @date 2018/6/9 1:14
 */

public class MatrixException extends Exception{

    /**
     *异常信息
     */
    private String message;

    /**
     * 无参构造器
     */
    public MatrixException() {
    }

    /**
     * 传入异常信息的构造器
     * @param message 异常信息
     */
    public MatrixException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * 获取异常信息
     * @return 异常信息
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 设置异常信息
     * @param message 要设置的异常信息
     */
    public void setMessage(String message) {
        this.message = message;
    }
}