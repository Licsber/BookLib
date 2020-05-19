package site.licsber.book.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

// 指导书上的方法
public class DbCones {
    protected Connection connection;

    public DbCones() {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bookLib");
            connection = ds.getConnection();
            if (connection == null) {
                System.out.println("conn is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
