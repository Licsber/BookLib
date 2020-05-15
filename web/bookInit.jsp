<%@ page import="site.licsber.book.entity.Book" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: licsber
  Date: 2020/5/14
  Time: 10:30 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Init</title>
</head>
<body>

<%
    HashMap<String, Book> books = new HashMap<>();
    String[] bookNo = {"1001", "1002", "1003", "1004"};
    String[] bookCover = {"book01.jpg", "book02.jpg", "book03.jpg", "book04.jpg"};
    String[] bookName = {"零基础设计从自学到入门到职场进阶", "JSP&Servlet学习笔记", "JavaScript高级程序设计", "Java编程思想"};
    float[] nowPrice = {60, 59, 99, 90};
    float[] orgPrice = {52, 40, 78, 89};
    int[] comments = {99, 23, 2, 45};
    String[] Author = {"李凯涛", "林信良", "Nicholas", "Len"};
    String[] PressDate = {"2018-09-01", "2018-09-01", "2018-01-10", "2020-04-20"};
    String[] Press = {"人民邮电出版社", "清华大学出版社", "南京大学出版社", "重庆大学出版社"};
    int[] bookNums = {0, 0, 0, 0};
    for (int i = 0; i < 4; i++) {
        Book book = new Book();
        book.setBookNo(bookNo[i]);
        book.setBookCover(bookCover[i]);
        book.setBookName(bookName[i]);
        book.setNowPrice(nowPrice[i]);
        book.setOrgPrice(orgPrice[i]);
        book.setComments(comments[i]);
        book.setAuthor(Author[i]);
        book.setPressDate(PressDate[i]);
        book.setPress(Press[i]);
        book.setBookNum(bookNums[i]);
        books.put(bookNo[i], book);
    }
%>

<%
    session.setAttribute("books", books);
    response.sendRedirect("bookList.jsp");
%>

</body>
</html>
