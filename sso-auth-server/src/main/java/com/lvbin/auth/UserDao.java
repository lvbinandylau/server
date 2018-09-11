package com.lvbin.auth;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "name"),
            @Result(property = "password", column = "pwd"),
            @Result(property = "role", column = "role")
    })
    @Select("select id, name, pwd, role from user WHERE name = #{name}")
    SysUser findByName(@Param("name") String name);

    @Select("select name from user WHERE name = #{name}")
    String UserExisted(@Param("name") String name);

    @Insert("insert into user(name, pwd, role) values (#{name}, #{pwd}, #{role})")
    void InsertUser(@Param("name") String name, @Param("pwd") String pwd, @Param("role") String role);
}
