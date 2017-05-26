import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by adam on 2017/5/22.
 */
public class BlogMain {
    public static void main(String[] args) {
        DataSource dataSource = null;
        try {
            //dataSource = BlogDataSourceFactory.getBlogDataSource();
            dataSource = BlogDataSourceFactory.getDruidDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(BlogMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Integer test1 = mapper.insert("测试1");
            Integer test2 = mapper.insert("测试2");
            Integer test3 = mapper.insert("测试3");
            System.out.println(test1 + "-->" + test2 + "-->" + test3);

            List<Blog> blogList1 = mapper.listBlog(10, 0);
            System.out.println(blogList1.toString());

            session.commit();
            Integer update = mapper.update(5, "测试1-->2");
            System.out.println("update-->" + update);

            Integer delete = mapper.delete(5);
            System.out.println("delete-->" + delete);

            session.commit();

            Blog blog = mapper.getBlog(1);
            System.out.println(blog);

            List<Blog> blogList = mapper.listBlog(10, 0);
            System.out.println(blogList.toString());

        } finally {
            session.close();
        }
    }
}
