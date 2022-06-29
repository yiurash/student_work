package com.exam.util;

import java.sql.*;

/**
 * @Author: wangzy
 * @Description: jdbc工具类
 * @Project Name:com.exam.test
 * @File Name: JdbcUtils
 * @Package Name:tdh.com.exam.test.jdbc
 * @Copyright (c) 2021,南京通达海信息科技有限公司 All Rights Reserved.
 * Modification History:
 * @Date @TIME      @Author      @Version  Description
 * ------------------------------------------------------------------
 * 2021/04/11 13:57  wangzy     1.0        1.0 Version
 */
public final class JdbcUtils {
    /**
     * 数据量url 时差问题
     */
    private static String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    /**
     * 数据库用户名
     */
    private static String user = "root";
    /**
     * 数据库密码
     */
    private static String password = "root";

    /**
     * 获取数据库连接
     *
     * @return Connection 数据库连接
     * @throws SQLException           异常信息
     * @throws ClassNotFoundException 异常信息
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 关闭数据库资源
     *
     * @param rs   ResultSet
     * @param st   Statement
     * @param conn Connection
     */
    public static void close(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}

