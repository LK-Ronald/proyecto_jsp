<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 04/09/2026
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Seccion</title>
</head>
<body>
<h1 style="text-align: center; color: green">Iniciar Seccion</h1>
<center>
    <form action="${pageContext.request.contextPath}/usuario?accion=login" method="post">
        <table style="margin: auto">
            <tr>
                <th style="text-align: right">ID:</th>
                <td><input type="text" name="id"></td>
            </tr>
            <tr>
                <th style="text-align: right">Password:</th>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td align="center" colspan="2" style="padding-top: 10px">
                    <input type="submit" value="Iniciar Seccion">
                    <input type="reset" value="Limpiar">
                </td>
            </tr>
        </table>
    </form>
    <p style="color: red">${mensaje}</p>
</center>
</body>
</html>
