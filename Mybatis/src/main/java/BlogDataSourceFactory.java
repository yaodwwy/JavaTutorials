import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by adam on 2017/5/22.
 */
public class BlogDataSourceFactory {
    public static DataSource getBlogDataSource() {
        Properties properties = new Properties();
        properties.setProperty("driver", "org.postgresql.Driver");
        properties.setProperty("url", "jdbc:postgresql://localhost:5432/postgres");
        properties.setProperty("username", "postgres");
        properties.setProperty("password", "123456");
        PooledDataSourceFactory pooledDataSourceFactory = new PooledDataSourceFactory();
        pooledDataSourceFactory.setProperties(properties);
        return pooledDataSourceFactory.getDataSource();
    }

    public static DataSource getDruidDataSource() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("url", "jdbc:postgresql://localhost:5432/postgres");
        properties.setProperty("driverClassName", "org.postgresql.Driver");
        properties.setProperty("username", "postgres");
        properties.setProperty("password", "123456");
        properties.setProperty("filters", "stat");
        properties.setProperty("maxActive", "20");
        properties.setProperty("initialSize", "1");
        properties.setProperty("maxWait", "60000");
        properties.setProperty("minIdle", "10");
        properties.setProperty("timeBetweenEvictionRunsMillis", "60000");
        properties.setProperty("minEvictableIdleTimeMillis", "300000");
        properties.setProperty("validationQuery", "SELECT 'x'");
        properties.setProperty("testWhileIdle", "true");
        properties.setProperty("testOnBorrow", "false");
        properties.setProperty("testOnReturn", "false");
        properties.setProperty("poolPreparedStatements", "true");
        properties.setProperty("maxOpenPreparedStatements", "20");
        return DruidDataSourceFactory.createDataSource(properties);
    }
}
