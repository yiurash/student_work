package com.exam.mapper;

import com.exam.entity.ReadItem;
import com.exam.util.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 封装了简易的crud
 */
public class Crud {
    public ReadItem<String> readitem = null;

    public String sql;

    public String getSQL() {
        return sql;
    }

    public Crud(String SQL) {
        this.sql = SQL;

    }

    public Crud(String SQL, ReadItem<String> readItem) {
        this.sql = SQL;
        this.readitem = readItem;
    }

    public ReadItem<String> getReadItem() {
        return readitem;
    }

    public void setReadItem(ReadItem<String> readItem) {
        this.readitem = readItem;
    }

    public void setSQL(String SQL) {
        this.sql = SQL;
    }

    /**
     * 读取数据
     *
     * @return
     * @throws Exception 异常信息
     */
    public List<List<Object>> read() throws Exception {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        List<List<Object>> result = new ArrayList<>();
        try {
            // 2.建立连接
            conn = JdbcUtils.getConnection();
            // conn = JdbcUtilsSing.getInstance().getConnection();
            // 3.创建语句
            st = conn.createStatement();

            // 4.执行语句
            rs = st.executeQuery(this.sql);
            // 5.处理结果

            ReadItem<String> readItem = this.readitem;
            Iterator<String> iterator;
            List<Object> list;

            while (rs.next()) {
                if (rs == null) {
                    return null;
                }
                //迭代器一次执行完会存其相应状态
                iterator = readItem.getReadItem().iterator();

                list = new ArrayList<>();

                while (iterator.hasNext()) {

                    String temp = iterator.next();

                    list.add(rs.getObject(temp));
                }
                result.add(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, st, conn);
        }
        return result;
    }

    /**
     * 删除方法
     *
     * @throws Exception 异常信息
     */
    public int delete() {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            //建立连接
            conn = JdbcUtils.getConnection();

            // 创建语句
            st = conn.prepareStatement(this.sql);
            // 创建迭代器
            Iterator<String> iterator = this.readitem.readitemlist.iterator();
            while (iterator.hasNext()) {
                String temp = iterator.next();
                st.setString(1, temp);
            }
            // 执行语句
            int i = st.executeUpdate();
            return i;
        } catch (Exception e) {
            // 错误回滚
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return 0;
        } finally {
            JdbcUtils.close(null, st, conn);
        }
    }

    /**
     * 更新数据
     *
     * @throws Exception 异常信息
     */
    public int update() {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            //建立连接
            conn = JdbcUtils.getConnection();

            // 创建语句
            st = conn.prepareStatement(this.sql);
            // 创建迭代器
            Iterator<String> iterator = this.readitem.readitemlist.iterator();
            int index = 1;
            while (iterator.hasNext()) {
                String temp = iterator.next();
                if (temp == "" && index == 7) {
                    st.setNull(index++, Types.INTEGER);
                } else {
                    st.setString(index++, temp);
                }
            }
            // 执行语句
            int i = st.executeUpdate();

            return i;
        } catch (Exception e) {
            // 错误回滚
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return 0;
        } finally {
            JdbcUtils.close(null, st, conn);
        }
    }

    /**
     * 新增数据
     *
     * @throws Exception 异常信息
     */
    public int create() {
        Connection conn = null;
        PreparedStatement st = null;
        int i = 0;
        try {
            //建立连接
            conn = JdbcUtils.getConnection();

            // 创建语句
            st = conn.prepareStatement(this.sql);
            // 创建迭代器
            Iterator<String> iterator = this.readitem.readitemlist.iterator();
            int index = 1;
            while (iterator.hasNext()) {
                String temp = iterator.next();
                if (temp == "" && index == 8) {
                    st.setNull(index++, Types.INTEGER);
                } else {
                    st.setString(index++, temp);
                }
            }
            // 执行语句
            i = st.executeUpdate();

        } catch (Exception e) {
            System.out.println("插入失败");
            e.printStackTrace();
        } finally {
            if (conn == null) {
                JdbcUtils.close(null, st, conn);
            }
        }
        return i;
    }
}
