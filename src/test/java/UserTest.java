import com.qcby.mapper.UserMapper;
import com.qcby.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserTest {
    private InputStream in = null;
    private SqlSession session = null;
    private UserMapper mapper = null;
    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        mapper = session.getMapper(UserMapper.class);
    }
    @After
    public void destory() throws IOException {
        //释放资源
        session.close();
        in.close();
    }
    @Test
    public void findId(){
        User user = mapper.findById(1);
        System.out.println(user);
    }

    @Test
    public void search(){
        User user = mapper.selectByUsername("'wkx' or username = '更新'");
        System.out.println(user);
    }

    @Test
    public void insert(){
        User user = new User("UIX","男","保定");
        int code = mapper.insert(user);
        session.commit();
        System.out.println(code);
    }
    @Test
    public void del(){
       int code =  mapper.delete(15);
       session.commit();
       System.out.println(code);
    }
    @Test
    public void update(){
        User user = new User("更新","女","北京");
        user.setId(1);
        int code = mapper.update(user);
        session.commit();
        System.out.println(code);
    }
    @Test
    public void findAll(){
        List<User> users = mapper.findAll();
        for (User user :users) {
            System.out.println(user);
        }
    }
    @Test
    public void insertGetId(){
        User user = new User("插入","男","邯郸");
        int count = mapper.insertGetId(user);
        session.commit();
        System.out.println(user.getId());

    }



}
