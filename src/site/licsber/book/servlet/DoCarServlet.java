package site.licsber.book.servlet;

import site.licsber.book.db.OrderDB;
import site.licsber.book.db.OrderDetailDB;
import site.licsber.book.entity.Book;
import site.licsber.book.entity.Order;
import site.licsber.book.entity.OrderDetail;
import site.licsber.book.utils.ShopCar;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
            case "removeselect": {
                String[] bookNos = request.getParameter("bookNos").split(" ");
                myCar.removeItem(bookNos);
                break;
            }
            case "立即购买": {
                String[] bookNos = request.getParameter("bookNos").split(" ");

                if (bookNos.length == 0) {
                    return;
                }

                String flag = (String) session.getAttribute("isLogin");
                if (flag == null || !flag.equals("true")) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                String userId = (String) session.getAttribute("username");
                double totalPrice = 0;
                Order order = new Order();
                order.createOrderId();
                order.setUserId(userId);
                order.setOrderTime(new Timestamp(new Date().getTime()));

                ArrayList<OrderDetail> orderDetails = new ArrayList<>();
                for (String bookNo : bookNos) {
                    OrderDetail detail = new OrderDetail();
                    Book book = myCar.getBuylist().get(bookNo);
                    detail.setOrderId(order.getOrderId());
                    detail.setBuyNum(book.getBookNum());
                    detail.setNowPrice(book.getNowPrice());
                    detail.setBookNo(bookNo);
                    orderDetails.add(detail);
                    totalPrice += book.getNowPrice() * book.getBookNum();
                }

                order.setTotalPrice(totalPrice);

                OrderDetailDB orderDetailDB = new OrderDetailDB();
                orderDetailDB.addOrderDetail(orderDetails);
                orderDetailDB.close();
                OrderDB orderDB = new OrderDB();
                orderDB.addOrder(order);
                orderDB.close();

                myCar.removeItem(bookNos);
                session.setAttribute("myCar", myCar);
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script>" +
                        "alert('购买成功');" +
                        "window.location='showCart.jsp';" +
                        "</script>");
                return;
            }
        }
        session.setAttribute("myCar", myCar);
        response.sendRedirect("showCart.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}