package site.licsber.book.servlet;

import site.licsber.book.db.BookDB;
import site.licsber.book.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/BookDisplayServlet")
public class BookDisplayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Book> books = BookDB.selectBook();
        HttpSession session = req.getSession();
        session.setAttribute("books", books);
        req.getRequestDispatcher("bookList.jsp").forward(req, resp);
    }
}
