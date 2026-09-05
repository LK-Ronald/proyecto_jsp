<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 05/09/2026
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Agregar Usuario</title>
</head>
<body>
<center>
    <h1 style="color: green; text-align: center">Agregar Usuario</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/usuario?accion=agregar" method="post">
        <table>
            <tr>
                <th style="text-align: right">ID:</th>
                <td><input type="text" name="id"></td>
            </tr>
            <tr>
                <th style="text-align: right">Nombre:</th>
                <td><input type="text" name="nombre"></td>
            </tr>
            <tr>
                <th style="text-align: right">Correo:</th>
                <td><input type="text" name="correo"></td>
            </tr>
            <tr>
                <th style="text-align: right">Password:</th>
                <td><input type="text" name="password"></td>
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
                <td><input type="submit" name="Agregar"></td>
                <td><input type="reset" name="Limpiar"></td>
            </tr>
        </table>
    </form>
    <p style="color: green">${param.mensaje}</p>
</center>
<hr>
<a href="${pageContext.request.contextPath}/index.jsp">Volver al Menu</a>
</body>
</html>
