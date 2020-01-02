package com.baizhi.controller;

import com.baizhi.entity.Users;
import com.baizhi.es.CustomUserRepository;
import com.baizhi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("users")
public class UsersController {
    //注入成员变量
    @Autowired
    private UsersService usersService;
    @Autowired
    private CustomUserRepository customUserRepository;

    //查询所有
    @ResponseBody
    @RequestMapping("showUsers")
    public List<Users> showUsers() {
        return usersService.queryAll();
    }
    //全文检索
    @ResponseBody
    @RequestMapping("elasticsearch")
    public List<Users> elasticsearch(String es){
        List<Users> highlight = customUserRepository.highlight(es);
        System.out.println(highlight);
        return highlight;

    }
    @ResponseBody
    @RequestMapping("queryByPage")
    //分页查
    public Map<String,Object> queryByPage(Integer rows,Integer page){
          Map<String,Object> map =  usersService.queryByPage(rows, page);
          return map;
    }
    //一堆操作
    @ResponseBody
    @RequestMapping("edit")
    public void edit(Users users,String oper){
        //如果add等于oper就添加
        if ("add".equals(oper)){
            //调用添加的方法
            usersService.addUsers(users);
        }else if("edit".equals(oper)){
            //修改
            usersService.modfilyUsers(users);
        }else if("del".equals(oper)){
            //删除
            usersService.removeUsers(users.getId());
        }
    }



    //根据id查
    @ResponseBody
    @RequestMapping("showById")
    public Users showById(String id) {
        Users queryById = usersService.queryById(id);
        return queryById;
    }

    //模糊查
    @ResponseBody
    @RequestMapping("queryByLike")
    public List<Users> queryByLike(Users users) {
        return usersService.queryLikeByUsers(users);
    }



    //退出
    @RequestMapping("quit")
    public String quit(HttpSession session) {
        session.invalidate();
        return "html/head";
    }
}
