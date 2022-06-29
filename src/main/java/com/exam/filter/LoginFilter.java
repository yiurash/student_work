package com.exam.filter;


import com.exam.entity.TUser;
import com.exam.service.SelectUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * 完成登录拦截 ServletRequest有所有请求头 可以处理任何传递信息
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "*.jsp", dispatcherTypes = {})
public class LoginFilter implements Filter {

    @Autowired
    SelectUserService selectUserByIdPwd;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String requestURI = httpServletRequest.getRequestURI();
        int idx = requestURI.lastIndexOf("/");
        String endString = requestURI.substring(idx + 1);

        if (!(endString.equals("login.jsp"))) {
            //如果不一致再通过缓存判断
            try {
                if (!isLogin(httpServletRequest)) {
                    httpServletResponse.sendRedirect("login.jsp");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } catch (Exception e) {
                //如果出现问题禁止放行
                e.printStackTrace();
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        /**
         * 已经登录的情况看session
         */


    }

    boolean isLogin(HttpServletRequest httpServletRequest) throws Exception {

        String account = null;
        String pwd = null;

        HttpSession session = httpServletRequest.getSession();
        account = (String) session.getAttribute("account");
        pwd = (String) session.getAttribute("pwd");

        if (account == null || pwd == null) {
            return false;
        }

        TUser t_user = selectUserByIdPwd.selectUserByIdPwd(account, pwd);
        /**
         * 只有一种情况通过
         */
        if (t_user == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void destroy() {

    }
}
