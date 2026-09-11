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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar Calificaciones</title>
</head>
<body>
<h1 style="text-align: center; color: green">Listar Calificaciones</h1>
<hr>
<center>
    <c:choose>
        <c:when test="${empty requestScope.calificaciones}">
            <p style="color: red">No hay calificaciones registradas</p>
        </c:when>

        <c:otherwise>
            <table border="1">
                <tr>
                    <th>CID</th>
                    <th>Fecha</th>
                    <th>Estudiante</th>
                    <th>Docente</th>
                    <th>Asignatura</th>
                    <th>Carrera</th>
                    <th>Universidad</th>
                    <th>Periodo</th>
                    <th>Actividad</th>
                    <th>Nota</th>
                    <th>Acciones</th>
                </tr>
                <c:forEach items="${requestScope.calificaciones}" var="calificacion">
                    <tr>
                        <td>${calificacion.cid}</td>
                        <td>${calificacion.fecha}</td>
                        <td>${calificacion.estudiante}</td>
                        <td>${calificacion.docente}</td>
                        <td>${calificacion.asignatura}</td>
                        <td>${calificacion.carrera}</td>
                        <td>${calificacion.universidad}</td>
                        <td>${calificacion.periodo}</td>
                        <td>${calificacion.actividadEvaluada}</td>
                        <td>${calificacion.nota}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/calificacion?accion=cargar_editar_calificacion&cid=${calificacion.cid}"
                                  method="post">
                                <input type="submit" value="Editar">
                                <input type="submit"
                                       formaction="${pageContext.request.contextPath}/calificacion?accion=eliminar_calificacion&cid=${calificacion.cid}"
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
<a href="${pageContext.request.contextPath}/web/calificacion/agregar.jsp">&colon; Agregar Calificacion &colon;</a>
</body>
</html>
