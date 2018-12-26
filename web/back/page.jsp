<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2018/12/25
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${page.url}&currentPage=1">首页</a>

<c:if test="${page.currentPage>1}">
    <a href="${page.url}&currentPage=${page.currentPage-1}">上一页</a>
</c:if>

<c:if test="${page.currentPage<page.pageCount}">
    <a href="${page.url}&currentPage=${page.currentPage+1}">下一页</a>
</c:if>
<a href="${page.url}&currentPage=${page.pageCount}">尾页</a>
</body>
</html>
