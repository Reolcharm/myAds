/**
 * Created by IntelliJ IDEA.
 * User: K1
 * Date: 2019/9/15
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */

package me.reolcharm.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class AccessLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    /**
     * 最后再执行
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        Long startTime = (Long) currentContext.get("startTime");
        Long duration = System.currentTimeMillis() - startTime;
        String requestURI = request.getRequestURI();

        log.info("The requestURI: " + requestURI + ", The duration: " + duration + "ms.");
        return null;
    }
}
