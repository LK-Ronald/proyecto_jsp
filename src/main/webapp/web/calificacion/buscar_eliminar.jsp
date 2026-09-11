<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 11/09/2026
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Buscar y Eliminar Calificacion</title>
</head>
<body>
<center>
    <h1 style="text-align: center; color: green">Buscar o Eliminar Calificacion</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/calificacion?accion=buscar_calificacion" method="post">
        <table>
            <tr>
                <th style="text-align: right">CID:</th>
                <td><input type="number" name="cid" required></td>
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
        <c:when test="${empty requestScope['calificacion.buscar']}">
            <hr>
            <p style="color: crimson">${param.mensaje}</p>
            <hr>
        </c:when>

        <c:otherwise>
            <hr>
            <table>
                <tr>
                    <th style="text-align: right">CID:</th>
                    <td>${requestScope['calificacion.buscar'].cid}</td>
                </tr>
                <tr>
                    <th style="text-align: right">Fecha:</th>
                    <td>${requestScope['calificacion.buscar'].fecha}</td>
                </tr>
                <tr>
                    <th style="text-align: right">Estudiante:</th>
                    <td>${requestScope['calificacion.buscar'].estudiante}</td>
                </tr>
                <tr>
                    <th style="text-align: right">Docente:</th>
                    <td>${requestScope['calificacion.buscar'].docente}</td>
                </tr>
                <tr>
                    <th style="text-align: right">Asignatura:</th>
                    <td>${requestScope['calificacion.buscar'].asignatura}</td>
                </tr>
                <tr>
                    <th style="text-align: right">Carrera:</th>
                    <td>${requestScope['calificacion.buscar'].carrera}</td>
                </tr>
                <tr>
                    <th style="text-align: right">Universidad:</th>
                    <td>${requestScope['calificacion.buscar'].universidad}</td>
                </tr>
                <tr>
                    <th style="text-align: right">Periodo:</th>
                    <td>${requestScope['calificacion.buscar'].periodo}</td>
                </tr>
                <tr>
                    <th style="text-align: right">Actividad Evaluada:</th>
                    <td>${requestScope['calificacion.buscar'].actividadEvaluada}</td>
                </tr>
                <tr>
                    <th style="text-align: right">Nota:</th>
                    <td>${requestScope['calificacion.buscar'].nota}</td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center">
                        <form action="${pageContext.request.contextPath}/calificacion?accion=cargar_editar_calificacion&cid=${requestScope['calificacion.buscar'].cid}"
                              method="post">
                            <button type="submit">Editar Calificacion</button>
                            <button type="submit"
                                    formaction="${pageContext.request.contextPath}/calificacion?accion=eliminar_calificacion&cid=${requestScope['calificacion.buscar'].cid}">
                                Eliminar Calificacion
                            </button>
                        </form>
                    </td>
                </tr>
            </table>
            <hr>
        </c:otherwise>
    </c:choose>
</center>
<a href="${pageContext.request.contextPath}/index.jsp">Menu principal</a>
</body>
</html>
