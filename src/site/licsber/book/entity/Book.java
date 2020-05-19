package site.licsber.book.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Book {
    private String bookNo;
    private String bookCover;
    private String bookName;
    private float nowPrice;
    private float orgPrice;
    private int comment;
    private String author;
    private Date pressDate;
    private String press;
    private int bookNum;
}
