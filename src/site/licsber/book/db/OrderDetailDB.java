package site.licsber.book.db;

import site.licsber.book.entity.OrderDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDB extends DbCones {
    public void addOrderDetail(ArrayList<OrderDetail> orderDetails) {
        if (connection != null) {
            try {
                PreparedStatement pst = connection.prepareStatement("insert into " +
                        "orderDetail values(?, ?, ?, ?);");
                for (OrderDetail orderDetail : orderDetails) {
                    pst.setString(1, orderDetail.getOrderId());
                    pst.setString(2, orderDetail.getBookNo());
                    pst.setDouble(3, orderDetail.getNowPrice());
                    pst.setInt(4, orderDetail.getBuyNum());
                    pst.addBatch();
                }
                pst.executeBatch();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
