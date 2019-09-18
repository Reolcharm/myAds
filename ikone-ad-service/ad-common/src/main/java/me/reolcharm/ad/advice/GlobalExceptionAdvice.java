/**
 * Created by IntelliJ IDEA.
 * User: K1
 * Date: 2019/9/17
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
package me.reolcharm.ad.advice;

import me.reolcharm.ad.exception.AdException;
import me.reolcharm.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author K1
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {
    /**
     * <pre>{@code @ExceptionHandler(AdException.class)}</pre>
     * <p>表示要对哪种异常进行被标示方法处理. 当我们的程序中出现 new throw(AdExeption)时,
     * 就会被 GlobalExceptionAdvice 捕获到. <b>如果没写, 就是对全部的异常进行处理</b></p>
     *
     * @param req 异常抛出后会传递两个参数中的一个
     * @param ex  要被处理的异常类
     * @return CommonResponse<String> 异常处理也是一种响应
     */
    @ExceptionHandler(AdException.class)
    public CommonResponse<String> handlerAdException(HttpServletRequest req,
                                                     AdException ex) {
        /*初始化*/
        CommonResponse<String> commonResponse = new CommonResponse<>(-1, "System busy!");
        /*将异常信息包装到 commonResponse*/
        commonResponse.setData(ex.getMessage());
        return commonResponse;
    }
}
