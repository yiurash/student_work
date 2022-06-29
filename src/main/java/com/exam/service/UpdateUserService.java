package com.exam.service;

import com.exam.entity.ReadItem;
import com.exam.entity.TUser;
import com.exam.mapper.CodeToSome;
import com.exam.mapper.Crud;
import com.exam.mapper.UpdateUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.exam.mapper.CodeToSome.MAP_BM;
import static com.exam.mapper.CodeToSome.MAP_XB;

@Service
public class UpdateUserService {

    @Autowired
    UpdateUserMapper updateUserMapper;
    /**
     * 更新数据
     * 根据YHID更新T_USER表数据，入参为T_USER表java对象或Map
     * 要求同方法2.1中要求：
     * YHID：库中已有数据。
     *
     * @param t
     * @param yhid
     * @return
     */
    public  int updateUser(Object t, String yhid) {

        int i = updateUserMapper.updatetUser((TUser) (t), yhid);





        TUser t_user = (TUser) (t);
        if (MAP_BM == null || MAP_XB == null) {
            new CodeToSome();
        }
        for (Map.Entry<String, String> entry : MAP_BM.entrySet()) {
            if (entry.getValue().equals(t_user.yhbm)) {
                t_user.setYhbm(entry.getKey());
            }
        }
        for (Map.Entry<String, String> entry : MAP_XB.entrySet()) {
            if (entry.getValue().equals(t_user.yhxb)) {
                t_user.setYhxb(entry.getKey());
            }
        }
        String sql = "UPDATE T_USER SET YHXM=?,YHKL=?,YHXB=?,YHBM=?,CSRQ=?,SFJY=?,PXH=? WHERE YHID=?";
        int update = new Crud(sql, new ReadItem<>()
                .add(t_user.yhxm)
                .add(t_user.yhkl)
                .add(t_user.yhxb)
                .add(t_user.yhbm)
                .add(t_user.csrq)
                .add(t_user.sfjy)
                .add(t_user.pxh)
                .add(yhid)).update();
        return update;
    }

}
