/**
 * Created by IntelliJ IDEA.
 * User: K1
 * Date: 2019/9/17
 * Time: 9:16
 * To change this template use File | Settings | File Templates.
 */
package me.reolcharm.ad.advice;

import me.reolcharm.ad.annotation.IgnoreResponseAdvice;
import me.reolcharm.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author K1
 * 对响应进行统一的拦截
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice {
    /**
     * 判断响应是否被拦截
     *
     * @param returnType {@inheritDoc}
     *                   根据方法参数判断
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 类或者方法上标注了注解`@IgnoreResponseAdvice` 的, 不向前台返回统一响应
        if (returnType.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        if (returnType.hasMethodAnnotation(IgnoreResponseAdvice.class)) {
            return false;
        }
        return true;
    }

    /**
     * 再写入响应之前进行的操作, 在这里将 body包装成 commonResponse,并返回给前台
     *
     * @param body 代表响应对象
     */
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 初始化
        CommonResponse<Object> commonResponse = new CommonResponse<>(0, "");
        if (body == null) {
            return commonResponse;
        } else if (body instanceof CommonResponse) {
            commonResponse = (CommonResponse) body;
        } else {
            commonResponse.setData(body);
        }
        return commonResponse;
    }
}
