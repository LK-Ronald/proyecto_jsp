<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 04/09/2026
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mensaje</title>
</head>
<body>
<h2 style="color: firebrick">${param.mensaje}</h2>
<hr>
<a href="${pageContext.request.contextPath}/index.jsp"> Volver &colon;&colon;</a>
</body>
</html>
