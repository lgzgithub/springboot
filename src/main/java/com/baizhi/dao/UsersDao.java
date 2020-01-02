package com.baizhi.dao;

import com.baizhi.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersDao {
    //查所有
    public List<Users> selectAll();
    //根据id查
    public Users selectById(@Param("id") String id);
    //添加
    public void insert(Users users);
    //删除
    public void delete(String id);
    //修改
    public void update(Users users);
    //模糊查
    public List<Users> selectLikeByUsers(Users users);
    //分页查
    public List<Users> selectByPage(@Param("start") Integer start, @Param("rows") Integer rows);
    //总页数
    public Integer queryCount();

}
