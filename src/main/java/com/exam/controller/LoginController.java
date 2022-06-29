package com.exam.controller;

import com.exam.entity.TUser;
import com.exam.service.SelectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    SelectUserService selectUserByIdPwd;

    @RequestMapping("/login")
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String account = req.getParameter("zh");
        String pwd = req.getParameter("pwd");
        TUser t_user = null;
        try {
            t_user = selectUserByIdPwd.selectUserByIdPwd(account,pwd);
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
