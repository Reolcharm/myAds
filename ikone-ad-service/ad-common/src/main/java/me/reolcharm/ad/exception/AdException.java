/**
 * Created by IntelliJ IDEA.
 * User: K1
 * Date: 2019/9/17
 * Time: 11:52
 * To change this template use File | Settings | File Templates.
 */
package me.reolcharm.ad.exception;

/**
 * 自定义了一个异常类
 */
public class AdException extends Exception {
    public AdException(String message) {
        super(message);
    }
}
