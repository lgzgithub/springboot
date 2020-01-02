import com.baizhi.App;
import com.baizhi.dao.UsersDao;
import com.baizhi.entity.Users;
import com.baizhi.es.CustomUserRepository;
import com.baizhi.es.UserRepository;
import com.baizhi.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Slf4j
public class TestEs {
    //Logger log = LoggerFactory.getLogger(TestUsers.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserRepository customUserRepository;

    @Test
    public void test() {
        Users users = new Users("12", "憨憨被排挤", "123", new Date(), "123456", "激活");
        userRepository.save(users);
    }
    @Test
    public void test1(){
        List<Users> highlight = customUserRepository.highlight("语文");
        System.out.println(highlight);

    }
    public void result(List<Users> list){
        list.forEach(users -> System.out.println(users));
    }
}

