<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 05/09/2026
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar usuarios</title>
</head>
<body>
<h1 style="text-align: center; color: green">Listar Usuarios</h1>
<hr>
<center>
    <c:choose>
        <c:when test="${empty requestScope.usuarios}">
            <p style="color: red">No hay usuarios registrados</p>
        </c:when>

        <c:otherwise>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Correo</th>
                    <th>Acciones</th>
                </tr>
                <c:forEach items="${requestScope.usuarios}" var="usuario">
                    <tr>
                        <td>${usuario.id}</td>
                        <td>${usuario.nombre}</td>
                        <td>${usuario.correo}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/usuario?accion=cargar_editar_usuario&id=${usuario.id}"
                                  method="post">
                                <input type="submit" value="Editar">
                                <input type="submit"
                                       formaction="${pageContext.request.contextPath}/usuario?accion=eliminar_usuario&id=${usuario.id}"
                                       value="Eliminar">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</center>
<hr>
<a href="${pageContext.request.contextPath}/index.jsp">&laquo; Volver al inicio &colon;&colon;</a>
<p></p>
<a href="${pageContext.request.contextPath}/web/usuario/agregar.jsp">&colon; Agregar Usuario &colon;</a>
</body>
</html>
