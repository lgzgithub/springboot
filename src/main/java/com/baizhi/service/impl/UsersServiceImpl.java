package com.baizhi.service.impl;

import com.baizhi.dao.UsersDao;
import com.baizhi.entity.Users;
import com.baizhi.service.UsersService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersDao usersDao;

    //查询所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Users> queryAll() {
        List<Users> users = usersDao.selectAll();
        return users;
    }
    //根据id查
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Users queryById(String id) {
        Users users = usersDao.selectById(id);
        return users;
    }

    //添加
    @Override
    public void addUsers(Users users) {
        users.setId(UUID.randomUUID().toString());
        usersDao.insert(users);
    }
    //删除
    @Override
    public void removeUsers(String id) {
        usersDao.delete(id);
    }
    //修改
    @Override
    public void modfilyUsers(Users users) {
       usersDao.update(users);
    }
    //模糊查
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Users> queryLikeByUsers(Users users) {
        List<Users> users1 = usersDao.selectLikeByUsers(users);
        return users1;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object> queryByPage(Integer rows, Integer page) {
        /*
         *   计算起始
         *   jqGrid要求别虚这样返回
         *   page: 当前页
         *   rows: 数据
         *   total: 总页数
         *   records: 总条数
         *
         * */
        Integer start =(page-1)*rows;
        /**
         * 数据
         */
        List<Users> users = usersDao.selectByPage(start,rows);
        /**
         * 总条数
         */
        Integer count = usersDao.queryCount();
        /**
         * 计算总页数
         */
        Integer totalPage = count%rows == 0 ? count/rows:count/rows+1;
        HashMap<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",users);
        map.put("total",totalPage);
        map.put("records",count);
        return map;
    }
}
