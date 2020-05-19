package site.licsber.book.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class Order {
    private String orderId;
    private String userId;
    private Timestamp orderTime;
    private double totalPrice;

    // 实体类为什么要带方法 这不MVC(Model)
    public void createOrderId() {
        int machineId = 1;
        int hash = UUID.randomUUID().toString().hashCode();
        hash = hash < 0 ? -hash : hash;
        orderId = machineId + String.format("%015d", hash);
    }
}
