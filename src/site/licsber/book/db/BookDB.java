package site.licsber.book.db;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import site.licsber.book.entity.Book;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BookDB {
    public static HashMap<String, Book> selectBook() {
        HashMap<String, Book> books = new HashMap<>();
        DbConn db = DbConn.getInstance();
        String sql = "select * from book;";
        BeanListHandler<Book> handler = new BeanListHandler<>(Book.class);
        try {
            List<List<Book>> list = db.getList(sql, null, handler);
            for (Book book : list.get(0)) {
                books.put(book.getBookNo(), book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return books;
    }

    public static int addBook(Book book) {
        DbConn db = DbConn.getInstance();
        String sql = "INSERT INTO bookLib.book\n" +
                "(bookNo, bookCover, bookName, nowPrice, orgPrice,\n" +
                " comment, author, press, pressDate, bookNum)" +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] params = {book.getBookNo(), book.getBookCover(), book.getBookName(),
                String.valueOf(book.getNowPrice()), String.valueOf(book.getOrgPrice()),
                String.valueOf(book.getComment()), book.getAuthor(), book.getPress(),
                format.format(book.getPressDate()), String.valueOf(book.getBookNum())};
        int num;
        try {
            num = db.update(sql, params);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        return num;
    }

    public static void main(String[] args) {
        // test
        if (false) {
            Book book = new Book();
            book.setBookNo("1005");
            book.setPress("ceshi");
            book.setBookName("ceshi");
            book.setBookCover("book01.jpg");
            book.setPressDate(new Date());
            book.setBookNum(10);
            System.out.println(addBook(book));
        }
        System.out.println(selectBook());
    }
}
