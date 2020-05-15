package site.licsber.book.entity;

import lombok.Data;

@Data
public class Book {
    private Object bookCartsObj;
    private Object booksObj;
    private String bookNo;
    private String bookCover;
    private String bookName;
    private float nowPrice;
    private float orgPrice;
    private int comments;
    private String author;
    private String pressDate;
    private String press;
    private int bookNum;
}
