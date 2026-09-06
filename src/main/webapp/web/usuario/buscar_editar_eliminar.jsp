<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 05/09/2026
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Buscar, Editar o Eliminar Usuario</title>
</head>
<body>
<center>
    <h1 style="text-align: center; color: green">Buscar, Editar o Eliminar Usuario</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/usuario?accion=buscar_editar_eliminar" method="post">
        <table>
            <tr>
                <th style="text-align: right">ID:</th>
                <td><input type="text" name="id"></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Buscar">
                    <input type="reset" value="Limpiar">
                </td>
            </tr>
        </table>
    </form>

    <c:choose>
        <c:when test="${empty requestScope['usuario.buscar']}">
            <hr>
            <p style="color: crimson">${param.mensaje}</p>
            <hr>
        </c:when>

        <c:otherwise>
            <hr>
            <table>
                <tr>
                    <th style="text-align: right">ID:</th>
                    <td>${requestScope['usuario.buscar'].id}</td>
                </tr>
                <tr>
                    <th style="text-align: right">Nombre:</th>
                    <td>${requestScope['usuario.buscar'].nombre}</td>
                </tr>
                <tr>
                    <th style="text-align: right">Correo:</th>
                    <td>${requestScope['usuario.buscar'].correo}</td>
                </tr>
                <tr>
                    <th style="text-align: right">Password:</th>
                    <td>********</td>
                </tr>
                <tr>
                    <th style="text-align: right">Rol:</th>
                    <td>${requestScope['usuario.buscar'].rol}</td>
                </tr>
            </table>
            <hr>
        </c:otherwise>
    </c:choose>
</center>
<a href="${pageContext.request.contextPath}/index.jsp">Menu principal</a>
</body>
</html>
