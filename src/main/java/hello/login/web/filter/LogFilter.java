package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
//@ServletComponentScan
//@WebFilter(filterName = "logFilter" , urlPatterns = "/*")
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();
        try{
            log.info("REQUEST [{}][{}]",uuid,requestURI);
            chain.doFilter(request,response);
        }
        catch (Exception e){
            throw  e;
        }
        finally {
            log.info("RESPONSE [{}][{}]",uuid,requestURI);
        }

    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
        Filter.super.destroy();
    }
}
