package com.exam.servelt;

import com.exam.entity.TUser;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



/**
 * 对于保存和更新的信息处理及返回对应信息
 */
public class OperationServelt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            int i = insertUser(new TUser(yhid, yhxm, yhkl, yhxb, yhbm, csrq, sfjy, pxh));
            if (i == 0) {
                req.setAttribute("label", "保存失败");
                resp.getWriter().write("<script> alert('保存失败')</script>");
            } else {
                req.setAttribute("label", "保存成功");
                resp.getWriter().write("<script> alert('保存成功')</script>");
            }
            req.getRequestDispatcher("window.jsp").include(req, resp);
        } else if (type.equals("UPDATE")) {
            int i = updateUser(new TUser(yhid, yhxm, yhkl, yhxb, yhbm, csrq, sfjy, pxh), yhid);
            if (i == 0) {
                req.setAttribute("label", "更新失败");
                resp.getWriter().write("<script> alert('更新失败')</script>");
            } else {
                req.setAttribute("label", "更新成功");
                resp.getWriter().write("<script> alert('更新成功')</script>");
            }
            req.getRequestDispatcher("window.jsp").include(req, resp);
        } else if (type.equals("DELETE")) {
            int i = deleteUser(yhid);
            resp.getWriter().write("{\"number\":\"" + i + "\"}");
        } else if (type.equals("SELECT")) {
            try {
                List<TUser> tUsers = selectUserByIdBm(yhid, yhbm);
                JSONArray jsonArray1 = JSONArray.fromObject(tUsers);
                resp.getWriter().write(String.valueOf(jsonArray1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
