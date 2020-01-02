package com.baizhi.cache;

import com.baizhi.conf.GetBeanUtil;
import com.baizhi.util.SerializeUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.concurrent.locks.ReadWriteLock;

public class RedisCache implements Cache {
    /*
     * 提供一个有参构造方法并有一个String id 参数
     * id: 当前mapper.xml中的namespace
     * getId()方法  返回这个id
     *
     * */
    private String id;

    public RedisCache(String id) {
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }
    /*
       添加缓存
           *  o   key
           *  o1  value
           * */
    @Override
    public void putObject(Object o, Object o1) {
        //System.out.println("添加缓存");
        //通过自定义的工厂工具类获得StringReidsTemplate
        StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) GetBeanUtil.getBean(StringRedisTemplate.class);
        if (!ObjectUtils.isEmpty(o1)){
            HashOperations<String, Object, Object> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
            //将string 序列化为字符串
            String serialize = SerializeUtils.serialize(o1);
            stringObjectObjectHashOperations.put(id.toString(), o.toString(), serialize);
        }
    }

    /**
     *
     * 取缓存
     * o 相当于获取dao的类名全限定名
     * @return
     */
    @Override
    public Object getObject(Object o) {
        System.out.println("取缓存");
        StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) GetBeanUtil.getBean(StringRedisTemplate.class);
        HashOperations<String,Object,Object> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
        String o1 = (String) stringObjectObjectHashOperations.get(id.toString(),o.toString());
        if (!StringUtils.isEmpty(o1)){
            Object o2 = SerializeUtils.serializeToObject(o1);
            return  o2;
        }
        return  null;
    }

    @Override
    public Object removeObject(Object o) {
        System.out.println("removeObject");
        return null;
    }
    //清楚缓存
    @Override
    public void clear() {
        StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) GetBeanUtil.getBean(StringRedisTemplate.class);
        stringRedisTemplate.delete(id);
        System.out.println("清楚缓存");
    }

    @Override
    public int getSize() {
        return 0;
    }
    //redis  单线程  单进程
    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }


}
