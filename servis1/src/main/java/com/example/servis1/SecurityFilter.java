package com.example.servis1;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET,PUT,PATCH,DELETE,POST,OPTIONS");
        httpResponse.addHeader("Access-Control-Max-Age", "3600");
        httpResponse.addHeader("Access-Control-Allow-Headers",
                "Origin, x-requested-with, X-AUTH-TOKEN, REFRESH-TOKEN, Content-Type, Accept");
        if(httpRequest.getMethod().equalsIgnoreCase("OPTIONS"))
            httpResponse.setStatus(HttpServletResponse.SC_OK);
        else
            chain.doFilter(request, response);

    }
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
}