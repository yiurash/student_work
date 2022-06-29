package com.exam.mapper;

import com.exam.entity.TUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateUserMapper {
    int updatetUser(TUser tUser,String yhid);
}
