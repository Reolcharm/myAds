/**
 * Created by IntelliJ IDEA.
 * User: K1
 * Date: 2019/9/17
 * Time: 12:53
 * To change this template use File | Settings | File Templates.
 */
package me.reolcharm.ad.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * <p>添加了一个转换器, 该转换器实现 json object与 Java Object 的互转,
     * 对应的请求头就是 json 格式的即: <pre>{@code Content-Type: application/json}</pre>
     * 这样其他模块引用该通用模块后, 请求的响应和请求对象的处理都会通过这个消息转换器进行过滤
     * </p>
     *
     * @param converters {@inheritDoc}
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
