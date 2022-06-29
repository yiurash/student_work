package com.exam.mapper;
import com.exam.entity.TUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectUserMapper {
    List<TUser> selectUserById();

    List<TUser> selectAllUser();

    List<TUser> selectUserByIdBm(String yhid,String yhbm);

    TUser selectUserByIdPwd(String username,String password);

    List<String> selectUserId();
}
