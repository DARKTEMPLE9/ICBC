import com.mybatis.UserBean;
import com.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTestTest {

    @Test
    public void insertTest() {
        /*读取mybatis-config.xml配置文件*/
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            System.out.println("mybatis-config.xml文件不存在");
            e.printStackTrace();
        }
        /*获取sqlSessionFactory*/
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        /**
         * selectOne 所需要 参数
         * statement --- xml 中namespace + sql id
         * parameter
         * try (SqlSession session = sqlSessionFactory.openSession()) {
         *   Blog blog = session.selectOne(
         *     "org.mybatis.example.BlogMapper.selectBlog", 101);
         * }
         */
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserBean userBean = sqlSession.selectOne("com.mybatis.UserMapper.selectUser", 1);
        List<UserBean> list = sqlSession.selectList("com.mybatis.UserMapper.selectUserAll");
        System.out.println("根据sqlSessionFactory查询" + userBean);
    }

    @Test
    public void test2() {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            System.out.println("mybatis-config.xml文件不存在");
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserBean userBean = mapper.selectUser(1);
        System.out.println("使用annotation方式（mapper）查询 user:{}" + userBean);

    }


}