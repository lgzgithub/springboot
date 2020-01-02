package com.baizhi.es;


import com.baizhi.entity.Users;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<Users,String> {

}
