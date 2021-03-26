<%--
  Created by IntelliJ IDEA.
  User: arvin
  Date: 25/03/2021
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<jsp:forward page="Login/Login.jsp"></jsp:forward>
</html>