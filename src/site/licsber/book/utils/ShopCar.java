package site.licsber.book.utils;

import lombok.Getter;
import site.licsber.book.entity.Book;

import java.util.HashMap;

public class ShopCar {
    @Getter
    private final HashMap<String, Book> buylist;

    public ShopCar() {
        buylist = new HashMap<>();
    }

    public void addItem(Book book, int num) {
        if (book != null) {
            String bookNo = book.getBookNo();
            Book tmp = buylist.get(bookNo);
            if (tmp != null) {
                tmp.setBookNum(tmp.getBookNum() + num);
            } else {
                tmp = book;
                tmp.setBookNum(num);
                buylist.put(bookNo, tmp);
            }
        }
    }

    public void addItem(Book book) {
        addItem(book, 1);
    }

    public void removeItem(String bookNo) {
        buylist.remove(bookNo);
    }

    public void removeItem(String[] bookNos) {
        if (bookNos != null) {
            for (String bookNo : bookNos) {
                buylist.remove(bookNo);
            }
        }
    }

    public void minusItem(String bookNo) {
        Book temp = buylist.get(bookNo);
        if (temp != null) {
            int num = temp.getBookNum();
            if (num > 1) {
                temp.setBookNum(num - 1);
            } else {
                if (num == 1) {
                    buylist.remove(bookNo);
                }
            }
        }
    }

    public void clearCar() {
        buylist.clear();
    }

}