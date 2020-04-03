package com.city.blog.blog.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/29
 * Time: 0:00
 * Description: No Description
 *
 * Exception ：受检查的异常，这种异常是强制我们catch或throw的异常。你遇到这种异常必须进行catch或throw，如果不处理，编译器会报错。比如：IOException。
 * RuntimeException：运行时异常，这种异常我们不需要处理，完全由虚拟机接管。比如我们常见的NullPointerException，我们在写程序时不会进行catch或throw。
 * RuntimeException不需要在异常上级强制使用catch throw异常捕获，使得程序正常运行，运行到错误的时候才处理异常
 * @author Cheng
 */
public class CustomizeException extends RuntimeException {
    private String message;
    public CustomizeException(String message){
        this.message=message;
    }

    /**
     * @param errorCode
     * 类型为接口是因为接口的实现类枚举获取了调用者的信息，把参数设置到了接口，这里通过接口拿到，然后被CustomizeExceptionHandler捕捉，
     * 返回给前端设置
     */
    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message=errorCode.getMessage();
    }
    @Override
    public String getMessage() {
        return message;
    }
}