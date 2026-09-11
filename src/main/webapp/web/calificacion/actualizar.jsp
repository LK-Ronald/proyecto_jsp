<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 11/09/2026
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Actualizar Calificacion</title>
</head>
<body>
<center>
    <h1>Actualizar Calificacion</h1>

    <c:choose>
        <c:when test="${empty requestScope['calificacion.editar']}">
            <hr>
            <p style="color: crimson">Error al editar la calificacion</p>
            <a href="${pageContext.request.contextPath}/index.jsp">&laquo; Volver &Colon;</a>
        </c:when>

        <c:otherwise>
            <hr>
            <form action="${pageContext.request.contextPath}/calificacion?accion=actualizar_calificacion" method="post">
                <table>
                    <tr>
                        <th style="text-align: right">CID:</th>
                        <td><input name="cid" type="text" value="${requestScope['calificacion.editar'].cid}" readonly></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Estudiante:</th>
                        <td><input name="estudiante" type="text" value="${requestScope['calificacion.editar'].estudiante}" required></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Docente:</th>
                        <td><input name="docente" type="text" value="${requestScope['calificacion.editar'].docente}" required></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Asignatura:</th>
                        <td><input name="asignatura" type="text" value="${requestScope['calificacion.editar'].asignatura}" required></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Carrera:</th>
                        <td><input name="carrera" type="text" value="${requestScope['calificacion.editar'].carrera}" required></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Universidad:</th>
                        <td><input name="universidad" type="text" value="${requestScope['calificacion.editar'].universidad}" required></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Periodo:</th>
                        <td><input name="periodo" type="text" value="${requestScope['calificacion.editar'].periodo}" required></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Actividad Evaluada:</th>
                        <td><input name="actividadEvaluada" type="text" value="${requestScope['calificacion.editar'].actividadEvaluada}" required></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Nota:</th>
                        <td><input name="nota" type="number" step="any" min="0" max="5" value="${requestScope['calificacion.editar'].nota}" required></td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Actualizar">
                            <input type="submit" value="Cancelar"
                                   formaction="${pageContext.request.contextPath}/web/calificacion/buscar_eliminar.jsp">
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
