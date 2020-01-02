package com.baizhi.service;

import com.baizhi.entity.Users;

import java.util.List;
import java.util.Map;

public interface UsersService {
    //查所有
    public List<Users> queryAll();
    //根据iD查
    public Users queryById(String id);
    //添加
    public void addUsers(Users users);
    //删除
    public void removeUsers(String id);
    //修改
    public void modfilyUsers(Users users);
    //模糊查
    public List<Users> queryLikeByUsers(Users users);
    //分页查
    public Map<String,Object> queryByPage(Integer rows, Integer page);
}
