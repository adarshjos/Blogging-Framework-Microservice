package com.IAM.shoutOut.authorization.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingFilterPre extends ZuulFilter {
    private static final String PRE_FILTER="pre";
    private static Logger logger = Logger.getLogger(LoggingFilterPre.class.getName());

    @Override
    public String filterType() {
        return PRE_FILTER;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        logger.log(Level.INFO,"{0}--->::request to::: {1}",
                new Object[]{request.getMethod(),request.getRequestURL().toString()});
        return null;
    }
}
