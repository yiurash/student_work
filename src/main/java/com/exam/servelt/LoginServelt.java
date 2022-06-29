package com.exam.servelt;

import com.exam.entity.TUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



/**
 * 登录对应servelt  从session获取值
 */

public class LoginServelt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String account = req.getParameter("zh");
        String pwd = req.getParameter("pwd");
        TUser t_user = null;
        try {
            t_user = selectUserByIdPwd(account, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (t_user == null) {
            resp.getWriter().write("<script> alert('账号或密码不正确')</script>");
            req.getRequestDispatcher("login.jsp").include(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("account", account);
            session.setAttribute("pwd", pwd);
            resp.sendRedirect("index.jsp");
        }
    }
}
