package site.licsber.book.servlet;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import site.licsber.book.db.BookDB;
import site.licsber.book.entity.Book;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/DoBookAddServlet")
public class DoBookAddServlet extends HttpServlet {
    private ServletConfig config;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("bookAdd.html");
    }

    @Override
    public void init(ServletConfig config) {
        this.config = config;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            SmartUpload upload = new SmartUpload();
            upload.initialize(config, req, resp);
            upload.setAllowedFilesList("jpg,jpeg,bmp,png");
            upload.upload();

            upload.save("/Users/licsber/IdeaProjects/BookLib/web/img");

            String bookNo = upload.getRequest().getParameter("bookNo");
            String bookName = upload.getRequest().getParameter("bookName");
            String author = upload.getRequest().getParameter("author");
            String press = upload.getRequest().getParameter("press");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date pressDate = format.parse(upload.getRequest().getParameter("pressDate"));
            String bookNum = upload.getRequest().getParameter("bookNum");
            String orgPrice = upload.getRequest().getParameter("orgPrice");
            String nowPrice = upload.getRequest().getParameter("nowPrice");
            Book book = new Book();

            File file = upload.getFiles().getFile(0);
            book.setBookCover(file.getFileName());
            System.out.println(file.getFileName());

            book.setBookNo(bookNo);
            book.setBookName(bookName);
            book.setAuthor(author);
            book.setPress(press);
            book.setPressDate(pressDate);
            int num;
            if (bookNum == null || bookNum.trim().equals("")) {
                num = 0;
            } else {
                num = Integer.parseInt(bookNum);
            }
            book.setBookNum(num);

            float oPrice, nPrice;
            if (orgPrice == null || orgPrice.trim().equals("")) {
                oPrice = 0;
            } else {
                oPrice = Float.parseFloat(orgPrice);
            }
            if (nowPrice == null || nowPrice.trim().equals("")) {
                nPrice = 0;
            } else {
                nPrice = Float.parseFloat(nowPrice);
            }
            book.setOrgPrice(oPrice);
            book.setNowPrice(nPrice);

            BookDB.addBook(book);
            resp.sendRedirect("BookDisplayServlet");
        } catch (Exception e) {
            resp.getWriter().print(e.getMessage());
        }
    }
}
