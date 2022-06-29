package com.exam.controller;

import com.exam.entity.TUser;
import com.exam.service.DeleteUserService;
import com.exam.service.InsertUserService;
import com.exam.service.SelectUserService;
import com.exam.service.UpdateUserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class OperationController {

    @Autowired
    DeleteUserService deleteUser;
    @Autowired
    InsertUserService insertUser;
    @Autowired
    UpdateUserService updateUser;

    @Autowired
    SelectUserService selectUserByIdBm;

    @RequestMapping("/operation")
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String type = req.getParameter("type");
        String yhid = req.getParameter("yhid");
        String yhxm = req.getParameter("yhxm");
        String yhkl = req.getParameter("yhkl");
        String yhbm = req.getParameter("yhbm");
        String yhxb = req.getParameter("yhxb");
        String csrq = req.getParameter("csrq");
        if (csrq != null) {
            csrq = csrq.replace("-", "");
        }
        String pxh = req.getParameter("pxh");
        String sfjy = req.getParameter("sfjy");
        if (type.equals("IN")) {
            int i = insertUser.insertUser(new TUser(yhid, yhxm, yhkl, yhxb, yhbm, csrq, sfjy, pxh));
            if (i == 0) {
                req.setAttribute("label", "保存失败");
                resp.getWriter().write("<script> alert('保存失败')</script>");
            } else {
                req.setAttribute("label", "保存成功");
                resp.getWriter().write("<script> alert('保存成功')</script>");
            }
            req.getRequestDispatcher("window.jsp").include(req, resp);
        } else if (type.equals("UPDATE")) {
            int i = updateUser.updateUser(new TUser(yhid, yhxm, yhkl, yhxb, yhbm, csrq, sfjy, pxh), yhid);
            if (i == 0) {
                req.setAttribute("label", "更新失败");
                resp.getWriter().write("<script> alert('更新失败')</script>");
            } else {
                req.setAttribute("label", "更新成功");
                resp.getWriter().write("<script> alert('更新成功')</script>");
            }
            req.getRequestDispatcher("window.jsp").include(req, resp);
        } else if (type.equals("DELETE")) {
            int i = deleteUser.deleteUser(yhid);
            resp.getWriter().write("{\"number\":\"" + i + "\"}");
        } else if (type.equals("SELECT")) {
            try {
                List<TUser> tUsers = selectUserByIdBm.selectUserByIdBm(yhid, yhbm);
                JSONArray jsonArray1 = JSONArray.fromObject(tUsers);
                resp.getWriter().write(String.valueOf(jsonArray1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

