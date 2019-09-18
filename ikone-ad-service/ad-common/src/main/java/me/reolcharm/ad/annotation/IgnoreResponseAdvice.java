/**
 * Created by IntelliJ IDEA.
 * User: K1
 * Date: 2019/9/17
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
package me.reolcharm.ad.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author K1
 * 标注了该 注解的 类或者方法, 不被 CommonResponseDataAdvice 拦截到
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {
}
