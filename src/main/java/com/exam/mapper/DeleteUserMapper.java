package com.exam.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface DeleteUserMapper {
    int deleteUserById(String yhid);
}
