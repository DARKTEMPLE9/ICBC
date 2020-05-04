import com.mybatis.UserBean;
import com.mybatis.mapper.UserMapper;
import com.selfmybatis.session.*;

import java.io.IOException;
import java.io.InputStream;

public class TLselfMybastisTest {

    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();
        InputStream inputStream = TLselfMybastisTest.class.getClassLoader().getResourceAsStream("UserMapper");
        configuration.setInputStream(inputStream);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        DefaultSqlSession sqlSession = (DefaultSqlSession) sqlSessionFactory.openSession(configuration);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserBean userBean = mapper.selectUser(1);
        System.out.println("使用annotation方式（mapper）查询 user:{}" + userBean);

    }
}
