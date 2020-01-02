package com.baizhi.es;

import com.baizhi.entity.Users;

import java.util.List;

public interface CustomUserRepository {
    //6.使用过滤条件 term 查询 返回指定字段 根据某个字段排序 分页 高亮
    List<Users> highlight(String es);
}
