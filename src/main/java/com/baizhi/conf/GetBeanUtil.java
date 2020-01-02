package com.baizhi.conf;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
//当前类需要交给工厂管理
@Component
public class GetBeanUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    //获得当前spring工厂
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /*
     * 根据类型获得spring容器中的对象
     * */
    public static Object getBean(Class clazz) {
        Object bean = applicationContext.getBean(clazz);
        return bean;
    }
}
