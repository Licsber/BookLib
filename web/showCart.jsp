<%@ page import="site.licsber.book.entity.Book" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.HashMap" %>
<%--
  Created by IntelliJ IDEA.
  User: licsber
  Date: 2020/4/28
  Time: 11:11 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShowCart</title>
    <link href="css/showCart.css" rel="stylesheet">
</head>
<body>
<jsp:useBean id="myCar" class="site.licsber.book.utils.ShopCar" scope="session"/>
<%
    HashMap<String, Book> bookCar = myCar.getBuylist();
%>

<div id="content">
    <p>您的位置:<a href="bookList.jsp">首页</a> > 购物车</p>

    <div class="hover-square">

        <label class="navList_red_arrow">&nbsp;&nbsp;1.查看购物车&nbsp;&nbsp;&nbsp;></label>
        <label class="navList_gray_arrow">&nbsp;&nbsp;2.确认订单信息&nbsp;&nbsp;&nbsp;></label>
        <label class="navList_gray_arrow">&nbsp;&nbsp;3.付款到支付宝&nbsp;&nbsp;&nbsp;></label>
        <label class="navList_gray_arrow">&nbsp;&nbsp;4.确认收货&nbsp;&nbsp;&nbsp;></label>
        <label class="navList_gray_arrow">&nbsp;&nbsp;5.评价&nbsp;&nbsp;&nbsp;></label>

    </div>
    <br>
    <form action="DoCarServlet" method="post" name="myform">
        <table width="100%" boder="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="title">
                    <label for="allCheckBox"></label><input id="allCheckBox" type="checkbox" onclick="selectAll()"/>全选
                </td>
                <td class="title" colspan="2">书籍名称</td>
                <td class="title">单价（元）</td>
                <td class="title">数量</td>
                <td class="title">小计（元）</td>
                <td class="title">操作</td>
            </tr>
            <%
                request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
                if (bookCar == null || bookCar.size() == 0) {
            %>
            <tr height="100">
                <td colspan="7" align="center">没有商品可显示！</td>
            </tr>
            <%
            } else {
                Book book;
                for (HashMap.Entry<String, Book> entry : bookCar.entrySet()) {
                    book = entry.getValue();
            %>
            <tr class="num_input">
                <td class="cart_td_1">
                    <label>
                        <input name="cartCheckBox" type="checkbox" value="<%=entry.getKey()%>"
                               onclick="selectSingle()"/>
                    </label></td>
                <td class="cart_td_2">
                    <img src="img/<%=book.getBookCover()%>" alt="<%=book.getBookCover()%>"/></td>
                <td class="cart_td_3">
                    <a href="#"><%=book.getBookName() %>
                    </a></td>
                <td class="cart_td_4">
                    <%=book.getNowPrice() %>
                </td>
                <td class="cart_td_5">
                    <a href="DoCarServlet?action=minus&bookNo=<%=entry.getKey() %>">
                        <img src="img/minus.jpg" class="hand" alt="<%=book.getBookNo()%>"/>
                    </a>
                    <label>
                        <input name="num" type="text" value=<%=book.getBookNum() %> class="num_input"
                               readonly="readonly"/>
                    </label>
                    <a href="DoCarServlet?action=add&bookNo=<%=entry.getKey() %>">
                        <img src="img/add.jpg" class="hand" alt="<%=book.getBookNo()%>"/>
                    </a>
                </td>
                <td class="cart_td_6"><%=book.getNowPrice() * book.getBookNum() %>
                </td>
                <td class="cart_td_7">
                    <a href="DoCarServlet?action=removeone&bookNo=<%=entry.getKey() %>">删除</a>
                </td>
            </tr>
            <%
                    }
                }

            %>
        </table>
        <table width="100%" boder="0" cellspacing="0" cellpadding="0">
            <tr>
                <td bgcolor=white>
                    <a id="removeSelect" class="bn" href="DoCarServlet?action=removeselect&bookNos="
                       onclick="selectSingle()">删除所选</a>
                </td>
                <td class="shopEnd"><br/>商品总价（不含运费）:
                    <label id="total" class="yellow">0</label>元<br/><br/>
                    <a id='confirm' class="bn" type="submit" href="DoCarServlet?action=立即购买&bookNos=">立刻购买</a></td>
            </tr>
        </table>
    </form>
</div>
</body>

<script src="js/myCart.js"></script>

</html>
