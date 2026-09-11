<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 11/09/2026
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Agregar Calificacion</title>
</head>
<body>
<center>
    <h1 style="color: green; text-align: center">Agregar Calificacion</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/calificacion?accion=agregar_calificacion" method="post">
        <table>
            <tr>
                <th style="text-align: right">Estudiante:</th>
                <td><input type="text" name="estudiante" required></td>
            </tr>
            <tr>
                <th style="text-align: right">Docente:</th>
                <td><input type="text" name="docente" required></td>
            </tr>
            <tr>
                <th style="text-align: right">Asignatura:</th>
                <td><input type="text" name="asignatura" required></td>
            </tr>
            <tr>
                <th style="text-align: right">Carrera:</th>
                <td><input type="text" name="carrera" required></td>
            </tr>
            <tr>
                <th style="text-align: right">Universidad:</th>
                <td><input type="text" name="universidad" required></td>
            </tr>
            <tr>
                <th style="text-align: right">Periodo:</th>
                <td><input type="text" name="periodo" required></td>
            </tr>
            <tr>
                <th style="text-align: right">Actividad Evaluada:</th>
                <td><input type="text" name="actividadEvaluada" required></td>
            </tr>
            <tr>
                <th style="text-align: right">Nota:</th>
                <td><input type="number" step="any" min="0" max="5" name="nota" required></td>
            </tr>

            <tr>
                <td><input type="submit" value="Agregar Calificacion"></td>
                <td><input type="reset" value="Limpiar"></td>
            </tr>
        </table>
    </form>
    <p style="color: green">${param.mensaje}</p>
</center>
<hr>
<a href="${pageContext.request.contextPath}/index.jsp">Volver al Menu</a>
</body>
</html>
