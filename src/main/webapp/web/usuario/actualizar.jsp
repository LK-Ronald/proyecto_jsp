<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 06/09/2026
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Actualizar Usuario</title>
</head>
<body>
<center>
    <h1>Actualizar Usuario</h1>

    <c:choose>
        <c:when test="${empty requestScope['usuario.editar']}">
            <hr>
            <p style="color: crimson">Error al editar el usuario</p>
            <a href="${pageContext.request.contextPath}/index.jsp">&laquo; Volver &Colon;</a>
        </c:when>

        <c:otherwise>
            <hr>
            <form action="${pageContext.request.contextPath}/usuario?accion=actualizar_usuario" method="post">
                <table>
                    <tr>
                        <th style="text-align: right">ID:</th>
                        <td><input name="id" type="text" value="${requestScope['usuario.editar'].id}" readonly></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Nombre:</th>
                        <td><input name="nombre" type="text" value="${requestScope['usuario.editar'].nombre}"></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Correo:</th>
                        <td><input name="correo" type="text" value="${requestScope['usuario.editar'].correo}"></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Password:</th>
                        <td><input name="new_password" type="password"></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Rol:</th>
                        <td>
                            <select name="rol" id="rol">
                                <option value="Usuario">Usuario</option>
                                <option value="Administrador">Administrador</option>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <input type="submit" value="Actualizar">
                            <input type="submit" value="Cancelar"
                                   formaction="${pageContext.request.contextPath}/web/usuario/buscar_eliminar.jsp">
                        </td>
                    </tr>
                </table>
                <p style="color: crimson">${not empty requestScope.mensaje ? requestScope.mensaje : param.mensaje}</p>
            </form>
        </c:otherwise>
    </c:choose>
</center>
<a href="${pageContext.request.contextPath}/index.jsp">&laquo; Volver &Colon;</a>
</body>
</html>
