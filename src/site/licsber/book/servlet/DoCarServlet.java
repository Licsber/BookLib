package site.licsber.book.servlet;

import site.licsber.book.entity.Book;
import site.licsber.book.utils.ShopCar;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@WebServlet("/DoCarServlet")
public class DoCarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        @SuppressWarnings("unchecked")
        HashMap<String, Book> books = (HashMap<String, Book>) session.getAttribute("books");

        ShopCar myCar = (ShopCar) session.getAttribute("myCar");
        if (myCar == null) {
            myCar = new ShopCar();
        }
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        if (action.equals("buy")) {
            String bookNo = request.getParameter("bookNo");
            Book tmp = books.get(bookNo);
            myCar.addItem(tmp);
            session.setAttribute("myCar", myCar);
            response.sendRedirect("bookList.jsp");
            return;
        }

        switch (action) {
            case "add": {
                String bookNo = request.getParameter("bookNo");
                Book tmp = books.get(bookNo);
                myCar.addItem(tmp);
                break;
            }
            case "minus": {
                String bookNo = request.getParameter("bookNo");
                myCar.minusItem(bookNo);
                break;
            }
            case "removeone": {
                String bookNo = request.getParameter("bookNo");
                myCar.removeItem(bookNo);
                break;
            }
            case "removeselect":
                String[] bookNos = request.getParameter("bookNos").split(" ");
                myCar.removeItem(bookNos);
                break;
        }
        session.setAttribute("myCar", myCar);
        response.sendRedirect("showCart.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}