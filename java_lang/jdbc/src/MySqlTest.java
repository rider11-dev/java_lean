import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;

public class MySqlTest {
    static String url = "jdbc:mysql://rm-8vbjy34g96075qpoklo.mysql.zhangbei.rds.aliyuncs.com:3408/iot";
    static String user = "sa";
    static String password = "qWSxBNqb";

    public void Test() throws Exception {
        Connection conn = null;
        Statement stat = null;
        try {
            // Class.forName("com.mysql.jdbc.Driver");//不需要注册了
            // conn = getConnection();
            conn = getConnectionUsingDruid();
            stat = conn.createStatement();
            conn.setAutoCommit(false);
            Test_Update(stat);
            Test_Select(stat);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    Connection getConnectionUsingDruid() throws Exception {
        Properties props = new Properties();
        InputStream is = MySqlTest.class.getClassLoader().getResourceAsStream("druid.properties");
        props.load(is);
        DataSource ds = DruidDataSourceFactory.createDataSource(props);
        return ds.getConnection();
    }

    private void Test_Select(Statement stat) throws SQLException {
        String sql = "select serialnum,`status`,iot_provider,connecttime,lng from iot.iot_device_attribute where serialnum='123456789'";
        ResultSet rs = stat.executeQuery(sql);

        while (rs.next()) {// 每次遍历一行记录
            System.out.println("终端号:" + rs.getString("serialnum"));
            System.out.println("状态:" + rs.getInt("status"));
            System.out.println("iot通道:" + rs.getString("iot_provider"));
            System.out.println("连接时间:" + rs.getDate("connecttime"));
            System.out.println("经度:" + rs.getDouble("lng"));
        }
    }

    private void Test_Update(Statement stat) throws Exception {
        int count = stat.executeUpdate("update iot.iot_device_attribute set `status`=1 where serialnum='123456789'");
        System.out.printf("update影响行数:%d\n", count);
    }
}
