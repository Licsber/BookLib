package site.licsber.book.db;

import site.licsber.book.entity.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDB extends DbCones {
    public int addOrder(Order order) {
        int res = 0;
        if (connection != null) {
            try {
                PreparedStatement pst = connection.prepareStatement("insert " +
                        "into `order` values(?, ?, ?, ?);");
                pst.setString(1, order.getOrderId());
                pst.setString(2, order.getUserId());
                pst.setTimestamp(3, order.getOrderTime());
                pst.setDouble(4, order.getTotalPrice());
                res = pst.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return res;
    }
}
