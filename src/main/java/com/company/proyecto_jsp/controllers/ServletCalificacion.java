package com.company.proyecto_jsp.controllers;

import com.company.proyecto_jsp.domain.entities.Calificacion;
import com.company.proyecto_jsp.infrastructure.repository.CRUDCalificacion;
import com.company.proyecto_jsp.infrastructure.services.CalificacionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletCalificacion", urlPatterns = "/calificacion")
public class ServletCalificacion extends HttpServlet {
    private final CalificacionService SERVICE = new CalificacionService(new CRUDCalificacion());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String contextPath = request.getContextPath();
        String accion = request.getParameter("accion");

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        if (accion == null || accion.trim().isEmpty()) {
            response.sendRedirect(contextPath + "/index.jsp");
            return;
        }

        if ("listar_calificaciones".equals(accion)) {
            validateSesion(request, response);
            processListarCalificaciones(request, response, contextPath);
        } else {
            response.sendRedirect(contextPath + "/index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String contextPath = request.getContextPath();
        String accion = request.getParameter("accion");

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        if (accion == null || accion.trim().isEmpty()) {
            response.sendRedirect(contextPath + "/index.jsp");
            return;
        }

        switch (accion) {
            case "agregar_calificacion":
                processAgregarCalificacion(request, response, contextPath);
                break;
            case "buscar_calificacion":
                processBuscarCalificacion(request, response, contextPath);
                break;
            case "cargar_editar_calificacion":
                processCargarCalificacionParaEditar(request, response, contextPath);
                break;
            case "actualizar_calificacion":
                processActualizarCalificacion(request, response, contextPath);
                break;
            case "eliminar_calificacion":
                processEliminarCalificacion(request, response, contextPath);
                break;
            default:
                response.sendRedirect(contextPath + "/index.jsp");
        }
    }

    private void processAgregarCalificacion(HttpServletRequest request, HttpServletResponse response, String contextPath) throws ServletException, IOException {
        validateSesion(request, response);
        String estudiante = request.getParameter("estudiante");
        String docente = request.getParameter("docente");
        String asignatura = request.getParameter("asignatura");
        String carrera = request.getParameter("carrera");
        String universidad = request.getParameter("universidad");
        String periodo = request.getParameter("periodo");
        String actividadEvaluada = request.getParameter("actividadEvaluada");
        String notaStr = request.getParameter("nota");

        try {
            double nota = (notaStr != null && !notaStr.trim().isEmpty()) ? Double.parseDouble(notaStr.trim()) : 0.0;

            Calificacion calificacion = new Calificacion(0, estudiante, docente, asignatura, carrera, universidad, periodo, actividadEvaluada, nota);
            SERVICE.addCalificacion(calificacion);
            String mensaje = "Calificacion agregada con exito";
            response.sendRedirect(contextPath + "/web/calificacion/agregar.jsp?mensaje=" + mensaje);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            response.sendRedirect(contextPath + "/web/calificacion/agregar.jsp?mensaje=" + mensaje);
        }
    }

    private void processBuscarCalificacion(HttpServletRequest request, HttpServletResponse response, String contextPath) throws ServletException, IOException {
        validateSesion(request, response);
        String cid = request.getParameter("cid");
        try {
            if (cid == null || cid.trim().isEmpty()) {
                throw new Exception("El ID de la calificacion es obligatorio");
            }
            int cidNormalizado = Integer.parseInt(cid.trim());
            Calificacion calificacion = SERVICE.getCalificacionById(cidNormalizado);
            request.setAttribute("calificacion.buscar", calificacion);
            request.getRequestDispatcher("/web/calificacion/buscar_eliminar.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(contextPath + "/web/calificacion/buscar_eliminar.jsp?mensaje=El ID debe ser un numero valido");
        } catch (Exception e) {
            response.sendRedirect(contextPath + "/web/calificacion/buscar_eliminar.jsp?mensaje=" + e.getMessage());
        }
    }

    private void processCargarCalificacionParaEditar(HttpServletRequest request, HttpServletResponse response, String contextPath) throws ServletException, IOException {
        validateSesion(request, response);
        String cid = request.getParameter("cid");
        try {
            if (cid == null || cid.trim().isEmpty()) {
                throw new Exception("El ID de la calificacion es obligatorio");
            }
            int cidNormalizado = Integer.parseInt(cid.trim());
            Calificacion calificacion = SERVICE.getCalificacionById(cidNormalizado);
            request.setAttribute("calificacion.editar", calificacion);
            request.getRequestDispatcher("/web/calificacion/actualizar.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(contextPath + "/web/calificacion/buscar_eliminar.jsp?mensaje=El ID debe ser un numero valido");
        } catch (Exception e) {
            response.sendRedirect(contextPath + "/web/calificacion/buscar_eliminar.jsp?mensaje=" + e.getMessage());
        }
    }

    private void processActualizarCalificacion(HttpServletRequest request, HttpServletResponse response, String contextPath) throws ServletException, IOException {
        validateSesion(request, response);
        String cid = request.getParameter("cid");
        String estudiante = request.getParameter("estudiante");
        String docente = request.getParameter("docente");
        String asignatura = request.getParameter("asignatura");
        String carrera = request.getParameter("carrera");
        String universidad = request.getParameter("universidad");
        String periodo = request.getParameter("periodo");
        String actividadEvaluada = request.getParameter("actividadEvaluada");
        String notaStr = request.getParameter("nota");

        try {
            if (cid == null || cid.trim().isEmpty()) {
                throw new Exception("El ID de la calificacion es obligatorio");
            }
            int cidNormalizado = Integer.parseInt(cid.trim());
            double nota = (notaStr != null && !notaStr.trim().isEmpty()) ? Double.parseDouble(notaStr.trim()) : 0.0;

            Calificacion calificacion = new Calificacion(cidNormalizado, estudiante, docente, asignatura, carrera, universidad, periodo, actividadEvaluada, nota);
            SERVICE.updateCalificacion(calificacion);
            String mensaje = "Calificacion actualizada con exito";
            response.sendRedirect(contextPath + "/web/calificacion/buscar_eliminar.jsp?mensaje=" + mensaje);
        } catch (Exception e) {
            try {
                int cidNormalizado = (cid != null && !cid.trim().isEmpty()) ? Integer.parseInt(cid.trim()) : 0;
                double nota = (notaStr != null && !notaStr.trim().isEmpty()) ? Double.parseDouble(notaStr.trim()) : 0.0;
                request.setAttribute("calificacion.editar", new Calificacion(cidNormalizado, estudiante, docente, asignatura, carrera, universidad, periodo, actividadEvaluada, nota));
                request.setAttribute("mensaje", e.getMessage());
                request.getRequestDispatcher("/web/calificacion/actualizar.jsp").forward(request, response);
            } catch (Exception ex) {
                String mensaje = e.getMessage();
                response.sendRedirect(contextPath + "/web/calificacion/actualizar.jsp?mensaje=" + mensaje);
            }
        }
    }

    private void processEliminarCalificacion(HttpServletRequest request, HttpServletResponse response, String contextPath) throws IOException {
        validateSesion(request, response);
        String cid = request.getParameter("cid");
        try {
            if (cid == null || cid.trim().isEmpty()) {
                throw new Exception("El ID de la calificacion es obligatorio");
            }
            int cidNormalizado = Integer.parseInt(cid.trim());
            SERVICE.deleteCalificacion(cidNormalizado);
            String mensaje = "Calificacion eliminada con exito";
            response.sendRedirect(contextPath + "/web/calificacion/buscar_eliminar.jsp?mensaje=" + mensaje);
        } catch (NumberFormatException e) {
            response.sendRedirect(contextPath + "/web/calificacion/buscar_eliminar.jsp?mensaje=El ID debe ser un numero valido");
        } catch (Exception e) {
            String mensaje = e.getMessage();
            response.sendRedirect(contextPath + "/web/calificacion/buscar_eliminar.jsp?mensaje=" + mensaje);
        }
    }

    private void processListarCalificaciones(HttpServletRequest request, HttpServletResponse response, String contextPath) throws ServletException, IOException {
        validateSesion(request, response);
        try {
            List<Calificacion> calificaciones = SERVICE.getAllCalificacion();
            request.setAttribute("calificaciones", calificaciones);
        } catch (Exception e) {
            response.sendRedirect(contextPath + "/web/mensaje.jsp?mensaje=" + e.getMessage());
            return;
        }
        request.getRequestDispatcher("/web/calificacion/listar.jsp").forward(request, response);
    }

    private void validateSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        boolean isAuthenticated = (session != null && session.getAttribute("usuario") != null);

        if (!isAuthenticated) {
            String loginUrl = request.getContextPath() + "/login.jsp";
            response.sendRedirect(loginUrl);
        }
    }
}
