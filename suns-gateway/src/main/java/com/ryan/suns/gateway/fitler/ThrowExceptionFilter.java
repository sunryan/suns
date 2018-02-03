package com.ryan.suns.gateway.fitler;

import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lr
 * @date 2018/2/3.
 */
@Slf4j
public class ThrowExceptionFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        log.info("This is a pre filter, it will throw a RuntimeException");
        doSomething();
        return null;
    }

    private void doSomething() {
        throw new RuntimeException("Exist some errors...");
    }
}
