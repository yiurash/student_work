package com.exam.mapper;

import com.exam.entity.ReadItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeToSome {
    public static List<List<Object>> READ_BM;
    public static List<List<Object>> READ_XB;
    public static Map<String, String> MAP_XB;
    public static Map<String, String> MAP_BM;

    /**
     * 静态代码块直接map数据加载
     */
    static {
        MAP_XB = new HashMap<>();
        MAP_BM = new HashMap<>();
        try {
            READ_BM = new Crud("SELECT BMDM,BMMC FROM T_DEPART", new ReadItem<>().add("BMDM").add("BMMC")).read();
            READ_XB = new Crud("SELECT CODE,MC FROM TS_BZDM", new ReadItem<>().add("CODE").add("MC")).read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (List<Object> objects : READ_XB) {
            MAP_XB.put((String) objects.get(0), (String) objects.get(1));
        }
        for (List<Object> objects : READ_BM) {
            MAP_BM.put((String) objects.get(0), (String) objects.get(1));
        }
    }

    /**
     * 实现以下2个方法：(MAP)
     * 根据给定的部门代码，转换成部门名称（查询T_DEPART表）。
     * 根据给定的用户性别代码，转换成用户性别名称（查询TS_BZDM表）。
     * 要求如下：
     * 每个方法仅可查询一次数据库；
     * 可连续调用此方法进行代码转换。
     * 静态代码块----->非静态代码块-------->构造函数
     *
     * @param in
     * @return String
     * @throws Exception
     */
    public static String codeToBmName(String in) {
        String target = null;
        for (String key : MAP_BM.keySet()) {
            if (in.equals(key)) {
                target = MAP_BM.get(in);
            }
        }
        return target;
    }

    /**
     * 实现以下2个方法：(MAP)
     * 根据给定的部门代码，转换成部门名称（查询T_DEPART表）。
     * 根据给定的用户性别代码，转换成用户性别名称（查询TS_BZDM表）。
     * 要求如下：
     * 每个方法仅可查询一次数据库；
     * 可连续调用此方法进行代码转换。
     *
     * @param in
     * @return String
     * @throws Exception
     */

    public static String codeToXbName(String in) {
        String target = null;
        for (String key : MAP_XB.keySet()) {
            if (in.equals(key)) {
                target = MAP_XB.get(in);
            }
        }
        return target;
    }

}
