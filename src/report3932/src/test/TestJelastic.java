package test;

import com.pengrad.rzd.report.Report;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.GregorianCalendar;

public class TestJelastic {

    static String username = "pengrad", password = "12345";
    public Connection createConnection() throws ClassNotFoundException, SQLException {

        Connection connection = null;

        // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
        // Create a connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://mysql-pengrad.jelastic.com/pengrad", username, password);

        return connection;
    }

    public static void main(String[] args) throws Exception {
        Connection c = new TestJelastic().createConnection();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("select 1 as A");
        rs.next();
        System.out.println(rs.getString("A"));
    }
}
