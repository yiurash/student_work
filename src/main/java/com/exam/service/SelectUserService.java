package com.exam.service;

import com.exam.entity.ReadItem;
import com.exam.entity.TUser;
import com.exam.mapper.Crud;
import com.exam.mapper.SelectUserMapper;
import com.exam.util.JdbcUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.exam.mapper.CodeToSome.*;

@Service
public class SelectUserService {

    @Autowired
    SelectUserMapper selectUserMapper;
    /**
     * 查询数 据
     * 根据YHID查询T_USER表数据，按行输出到后台
     * 要求如下：
     * YHBM、YHXB需要代码转换成汉字；
     * 转换方法调用方法1.1。
     *
     * @return
     * @throws Exception
     */
    public  List<List<Object>> selectUser() throws Exception {
        //通过封装 读取比较简易 add后面是所查行
        List<List<Object>> read = new Crud("SELECT YHID,YHXM,YHXB,YHBM FROM T_USER", new ReadItem<>().add("yhid").add("yhxm").add("yhxb").add("yhbm")).read();




        List<TUser> tUsers = selectUserMapper.selectUserById();
            for (TUser tUser : tUsers) {
                tUser.setYhxb(codeToXbName(tUser.getYhxb()));
                tUser.setYhbm(codeToBmName(tUser.getYhbm()));
            }




            for (List<Object> objects : read) {
                objects.set(2, codeToXbName((String) objects.get(2)));
                objects.set(3, codeToBmName((String) objects.get(3)));
        }
        return read;
    }

    public  List<List<Object>> selectUserAll() throws Exception {
        List<TUser> tUsers = selectUserMapper.selectAllUser();


        //通过封装 读取比较简易 add后面是所查行
        /**
         *  public String yhid;
         *     public String yhxm;
         *     public String yhkl;
         *     public String yhxb;
         *     public String yhbm;
         *     public String csrq;
         *     public String sfjy;
         *     public String pxh;
         *     查询用户所有信息
         */
        List<List<Object>> read = new Crud("SELECT YHID,YHXM,YHXB,YHBM,DJRQ,SFJY,PXH FROM T_USER", new ReadItem<>().add("yhid").add("yhxm").add("yhxb").add("yhbm").add("djrq").add("sfjy").add("pxh")).read();
        for (List<Object> objects : read) {
            objects.set(2, codeToXbName((String) objects.get(2)));
            objects.set(3, codeToBmName((String) objects.get(3)));
        }
        return read;
    }

    /**
     * public String yhid;
     * public String yhxm;
     * public String yhkl;
     * public String yhxb;
     * public String yhbm;
     * public String csrq;
     * public String sfjy;
     * public String pxh;
     *
     * @param id
     * @param yhbm
     * @return 根据id和部门查询
     * @throws Exception
     */
    public  List<TUser> selectUserByIdBm(String id, String yhbm) throws Exception {

        List<TUser> tUsers = selectUserMapper.selectUserByIdBm(id, yhbm);


        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<TUser> userArrayList = new ArrayList<>();
        ArrayList<String> paramsList = new ArrayList<>();
        try {
            connection = JdbcUtils.getConnection();
            StringBuffer sqlBuffer = new StringBuffer("SELECT YHID,YHXM,YHKL,YHXB,YHBM,CSRQ,SFJY,PXH FROM T_USER");
            if (id != null && !"".equals(id)) {
                sqlBuffer.append(" AND (YHID LIKE ? OR YHXM LIKE ?) ");
                paramsList.add("%" + id + "%");
                paramsList.add("%" + id + "%");
            }
            if (yhbm != null && !"".equals(yhbm)) {
                sqlBuffer.append(" AND YHBM LIKE ? ");
                paramsList.add("%" + yhbm + "%");
            }
            String sql = sqlBuffer.append(" ORDER BY PXH ASC").toString().replaceFirst("AND", "WHERE");
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 1; i <= paramsList.size(); i++) {
                preparedStatement.setString(i, paramsList.get(i - 1));
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TUser t_user = new TUser(
                        (String) resultSet.getObject("yhid"),
                        (String) resultSet.getObject("yhxm"),
                        (String) resultSet.getObject("yhkl"),
                        codeToXbName((String) resultSet.getObject("yhxb")),
                        codeToBmName((String) resultSet.getObject("yhbm")),
                        (String) resultSet.getObject("csrq"),
                        (String) resultSet.getObject("sfjy"),
                        String.valueOf(resultSet.getObject("pxh") == null ? "" : resultSet.getObject("pxh")));
                userArrayList.add(t_user);
            }
            return userArrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 根据用户账号密码查询
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public  TUser selectUserByIdPwd(String username, String password) throws Exception {

        TUser tUser = selectUserMapper.selectUserByIdPwd(username, password);



        if (username == "" || password == "") {
            return null;
        }
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<TUser> userArrayList = new ArrayList<>();
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement("SELECT YHID,YHKL FROM T_USER WHERE  YHID = ? AND  YHKL = ? ");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TUser t_user = new TUser();
                t_user.setYhid((String) resultSet.getObject("yhid"));
                t_user.setYhkl((String) resultSet.getObject("yhkl"));
                userArrayList.add(t_user);
            }
            if (userArrayList.size() > 1 || userArrayList.size() == 0) {
                return null;
            } else {
                return userArrayList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 查询用户id
     *
     * @return
     * @throws Exception
     */
    public  List<String> selectUserId() throws Exception {
        List<String> strings = selectUserMapper.selectUserId();



        ArrayList<String> useridlist = new ArrayList<>();
        List<List<Object>> read = new Crud("SELECT YHID FROM T_USER", new ReadItem<>().add("yhid")).read();
        for (Object userid : read
        ) {
            for (Object user : (List<Object>) userid
            ) {
                useridlist.add((String) user);
            }
        }
        return useridlist;
    }
}
