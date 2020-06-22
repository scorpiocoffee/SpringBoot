package com.demo.filter_interceptor.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Initialize Filter Controller");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Start to run Filter");
        Long start = new Date().getTime();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("The duration time for running Filter: " + (new Date().getTime() - start));
        System.out.println("The Filter ended");
    }

    @Override
    public void destroy() {
        System.out.println("Filter destroy");
    }
}
