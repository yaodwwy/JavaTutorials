package utils;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by Adam Yao on 2017/6/11
 */
public class JdbcTest {
    public static void main(String[] args) throws Exception{
        //16. 数据库访问
        // JDO
        Properties properties = new Properties();
        properties.load(new FileInputStream("jdo.properties"));
        PersistenceManagerFactory persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory(properties);
        PersistenceManager persistenceManager = persistenceManagerFactory.getPersistenceManager();
        // 提交数据
        persistenceManager.currentTransaction().begin();
        Collection collection;
        ArrayList<Object> objects = new ArrayList<>();
        if (objects instanceof Collection) {
            persistenceManager.makePersistentAll((Collection) objects);
        } else {
            persistenceManager.makePersistent(objects);
        }
        persistenceManager.currentTransaction().commit();
        persistenceManager.close();
        // 取出数据
        Object[] data = new Object[3];
        persistenceManager.retrieveAll(data);
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
        persistenceManager.close();

        // 数据操作
        Class clz = Class.forName("oracle.jdbc.driver.OracleDriver");
        String dbUrl = "jdbc:oracle:thin:@192.168.0.23:1521#:nms";
        Connection conn = DriverManager.getConnection(dbUrl, "su", "1234");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from pmtable");
        while (rs.next()) {
            String name = rs.getString(1);
            String otherName = rs.getString("name");
        }

        // 使用PreparedStatement提高性能，除了查询，都使用executeUpdate执行操作
        PreparedStatement pstmt = conn.prepareStatement("select * from pmtable where name = ?");
        pstmt.setString(1, "sean");
        ResultSet resultSet = pstmt.executeQuery();

        // 调用存储过程
        CallableStatement cs = conn.prepareCall("{ call ListDefunctUsers }");
        ResultSet resultSet1 = cs.executeQuery();

        // 显示数据库表信息
        DatabaseMetaData meta = conn.getMetaData();
        meta.getDatabaseProductName();
        meta.getDatabaseProductVersion();
        meta.getDefaultTransactionIsolation();
    }
}
