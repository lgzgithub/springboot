import com.baizhi.App;
import com.baizhi.dao.UsersDao;
import com.baizhi.entity.Users;
import com.baizhi.es.CustomUserRepository;
import com.baizhi.es.UserRepository;
import com.baizhi.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.el.CompositeELResolver;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Slf4j
public class TestUsers {
     //Logger log = LoggerFactory.getLogger(TestUsers.class);
     @Autowired
     private UsersDao usersDao;
     private UsersService usersService;
    @Autowired
    private CustomUserRepository customUserRepository;
     @Test
     public void test(){
         List<Users> users = usersDao.selectAll();
         for (Users user : users) {
             log.error("{}",user);
         }
     }
     @Test
    public void test1(){
         List<Users> users = usersService.queryAll();
         for (Users user : users) {
             log.error("{}",user);
         }
     }
    /* @Test
    public void test2(){
         List<Users> highlight = customUserRepository.highlight();
         result(highlight);
     }*/
     @Test
     public void test3(){
         Map<String, Object> queryByPage = usersService.queryByPage(1, 1);
         for (String s : queryByPage.keySet()) {
             System.out.println(s);
         }
     }

     public void result(List<Users> list){
         list.forEach(users -> System.out.println(users));
     }
}
