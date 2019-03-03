package com.demo.dao;

import com.demo.entity.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IUserRoleMapper {

    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);


    int insert(UserRole record);


    List<UserRole> selectAll();
    //根据用户id查询该用户所具有的角色
    List<UserRole> findByUserId(@Param("userId") Integer userId);

	int deleteByUserId(@Param("userId") int userId);
}