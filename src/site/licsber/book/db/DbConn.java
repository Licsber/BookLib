package site.licsber.book.db;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

// 自己的方法
public class DbConn {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/bookLib?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "";

    private Connection connection;

    private static DbConn singleInstance;

    public synchronized static DbConn getInstance() {
        if (singleInstance == null) {
            singleInstance = new DbConn();
        }
        return singleInstance;
    }

    private DbConn() {
        DbUtils.loadDriver(JDBC_DRIVER);
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            System.exit(0);
        }
    }

    private String getCmd(String sql, String[] params) {
        StringBuilder cmd = new StringBuilder();

        int count = 0;
        int len = sql.length();
        for (int i = 0; i < len; i++) {
            char ch = sql.charAt(i);
            if (ch == '?') {
                cmd.append('\'');
                cmd.append(params[count++]);
                cmd.append('\'');
            } else {
                cmd.append(ch);
            }
        }

        return cmd.toString();
    }

    public int update(String sql, String[] params) throws SQLException {
        QueryRunner runner = new QueryRunner();
        return runner.update(connection, getCmd(sql, params));
    }

    public <T> List<T> getList(String sql, String[] params, ResultSetHandler<T> handler) throws SQLException {
        QueryRunner runner = new QueryRunner();
        return runner.execute(connection, getCmd(sql, params), handler);
    }
}
