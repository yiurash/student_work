package com.exam.service;

import com.exam.entity.ReadItem;
import com.exam.entity.TUser;
import com.exam.mapper.CodeToSome;
import com.exam.mapper.Crud;
import com.exam.mapper.InsertUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.exam.mapper.CodeToSome.MAP_BM;
import static com.exam.mapper.CodeToSome.MAP_XB;

@Service
public class InsertUserService {

    @Autowired
    InsertUserMapper insertUserMapper;
    /**
     * 2.1、插入数据
     * 向T_USER表增加一条数据，入参为T_USER表java对象或Map
     * 要求如下：
     * YHID：自定义；
     * YHXM：调用方法1.2生成；
     * YHXB：数据随机来源于TS_BZDM.CODE；
     * YHBM：数据随机来源于T_DEPART.BMDM；
     * DJSJ：存储格式yyyy-MM-dd HH:mm:ss；
     * DJRQ：存储格式yyyy-MM-dd。
     *
     * @param t connection
     * @return String
     * @throws Exception
     */
    public  int insertUser(Object t) {

        int i1 = insertUserMapper.insertUser((TUser) t);

        TUser t_user = (TUser) t;
        if (MAP_BM == null || MAP_XB == null) {
            new CodeToSome();
        }
        for (Map.Entry<String, String> entry : MAP_BM.entrySet()) {
            System.out.println((t_user.yhbm));
            if (entry.getValue().equals(t_user.yhbm)) {
                t_user.setYhbm(entry.getKey());
            }
        }
        for (Map.Entry<String, String> entry : MAP_XB.entrySet()) {
            if (entry.getValue().equals(t_user.yhxb)) {
                t_user.setYhxb(entry.getKey());
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "INSERT INTO T_USER(YHID,YHXM,YHKL,YHXB,YHBM,CSRQ,SFJY,PXH,DJSJ,DJRQ) VALUES (?,?,?,?,?,?,?,?,?,?)";
        int i = new Crud(sql, new ReadItem<>()
                .add(t_user.yhid)
                .add(t_user.yhxm)
                .add(t_user.yhkl)
                .add(t_user.yhxb)
                .add(t_user.yhbm)
                .add(t_user.csrq)
                .add(t_user.sfjy)
                .add(t_user.pxh)
                .add(sdf.format(new Date()))
                .add(sdfd.format(new Date()))).create();
        return i;
    }
}
