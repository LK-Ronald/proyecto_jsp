<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina de inicio</title>
</head>
<body>
<h1 style="color: green; text-align: center">Bienvenido al Sistema</h1>

<c:choose>
    <c:when test="${empty sessionScope.usuario}">
        <p>No has iniciado sesion</p>
    </c:when>

    <c:otherwise>
        <p>${param.mensaje}</p>
        <table border="0">
            <tr>
                <td>&bull;<a href="${pageContext.request.contextPath}/web/usuario/agregar.jsp">Agregar Usuario</a></td>
            </tr>
            <tr>
                <td>&bull;<a
                        href="${pageContext.request.contextPath}/web/usuario/buscar_editar_eliminar.jsp">Buscar, Editar
                    o Eliminar Usuario</a>
                </td>
            </tr>
            <tr>
                <td>&bull;<a href="${pageContext.request.contextPath}/web/usuario/listar.jsp">Listar Usuario</a></td>
            </tr>
        </table>
    </c:otherwise>
</c:choose>

<a href="${pageContext.request.contextPath}/login.jsp">Iniciar Sesion</a>
</body>
</html>