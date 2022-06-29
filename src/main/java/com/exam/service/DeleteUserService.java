package com.exam.service;

import com.exam.entity.ReadItem;
import com.exam.mapper.Crud;
import com.exam.mapper.DeleteUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserService {

    @Autowired
    DeleteUserMapper deleteUserMapper;
    /**
     * 删除数据
     * 根据YHID删除T_USER表数据，入参为YHID
     *
     * @param yhid
     * @Throw Exception
     */
    public  int deleteUser(String yhid) {
        int i1 = deleteUserMapper.deleteUserById(yhid);

        try {
            //成功则返回影响行数
            int i = new Crud("DELETE FROM T_USER WHERE YHID=?", new ReadItem<>().add(yhid)).delete();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }
}
