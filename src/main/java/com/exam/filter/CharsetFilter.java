package com.exam.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * 简要的对进入和传出编码进行编辑
 * 用java自带注解 按首字母执行 如在web。xml里配置则按其配置执行优先
 */
@WebFilter(filterName = "CharsetFilter", urlPatterns = "/*", initParams = {
        @WebInitParam(name = "charset", value = "utf-8")/*这里可以放一些初始化的参数*/
})
public class CharsetFilter implements Filter {
    private String filterName;
    private String charset;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化会传入filterConfig WebFilter配置的WebInitParam
        filterName = filterConfig.getFilterName();
        charset = filterConfig.getInitParameter("charset");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(charset);
        servletResponse.setCharacterEncoding(charset);
        //传给下一个filter处理
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
