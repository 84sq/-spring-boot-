package org.sq.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Update("update t_user set password = '${password}' where userName = '${userName}' ")
    public int updatePassword(@Param("password") String password, @Param("userName") String userName);
}
