package site.licsber.book.db;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import site.licsber.book.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserDB {
    public static boolean isLogin(String userName, String userPwd) {
        DbConn db = DbConn.getInstance();
        String sql = "select * from user where username=? and userPwd=?;";
        String[] params = {userName, userPwd};
        BeanListHandler<User> handler = new BeanListHandler<>(User.class);
        List<List<User>> list;
        try {
            list = db.getList(sql, params, handler);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return list.get(0).size() == 1;
    }

    public static void main(String[] args) {
        // Test
        System.out.println(isLogin("123", "123"));
        System.out.println(isLogin("123", "1234"));
    }
}
