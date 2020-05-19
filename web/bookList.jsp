<%@ page import="site.licsber.book.entity.Book" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: licsber
  Date: 2020/4/28
  Time: 11:10 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    @SuppressWarnings("unchecked")
    HashMap<String, Book> books = (HashMap<String, Book>) session.getAttribute("books");
%>
<head>
    <title>ShowBooks</title>
    <link type="text/css" href="css/bookList.css" rel="stylesheet"/>
</head>
<body>

<% if (books == null || books.size() == 0) { %>

<div>没有商品可显示</div>

<%
} else {
    Book book;
    for (HashMap.Entry<String, Book> entry : books.entrySet()) {
        book = entry.getValue();
%>
<div>
    <ul>
        <li>
            <a class="pic" href="#" target="_blank">
                <img src="img/<%=book.getBookCover() %>" alt="<%=book.getBookName()%>"/>
            </a>
            <div class="desc">
                <p>
                    <%=book.getBookName() %>
                </p>
                <p>
                    <span>现价：&yen;&nbsp;<%=book.getNowPrice() %></span>
                    <span class="price">&nbsp;&nbsp;定价:
						<span>&yen;&nbsp;<%=book.getOrgPrice() %></span>
					</span>
                </p>
                <p>
                    <span class="star"></span>
                    <a href="#" target="_blank">❤❤❤❤❤<%=book.getComment() %>条评论</a>
                </p>
                <p>
                    <span><a href=""><%=book.getAuthor() %></a>著</span>
                    <span>/<%=book.getPressDate() %></span>
                    <span>/<a href=""><%=book.getPress() %></a></span>
                </p>
                <p class="detail"></p>
                <div>
                    <p>
                        <a class="bn" href="DoCarServlet?action=buy&bookNo=<%=entry.getKey()%>">加入购物车</a>
                        <a class="bn" href="#">收藏</a>
                    </p>
                </div>
            </div>
        </li>
    </ul>
</div>
<%
        }
    }
%>

<div class="bnCar">
    <a class="bn" href="showCart.jsp">查看购物车</a>
</div>

</body>
</html>
