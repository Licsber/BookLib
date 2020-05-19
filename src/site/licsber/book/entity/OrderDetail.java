package site.licsber.book.entity;

import lombok.Data;

@Data
public class OrderDetail {
    private String orderId;
    private String bookNo;
    private double nowPrice;
    private int buyNum;
}
