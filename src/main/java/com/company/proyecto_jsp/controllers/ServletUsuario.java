package com.company.proyecto_jsp.controllers;

import com.company.proyecto_jsp.domain.entities.Usuario;
import com.company.proyecto_jsp.domain.enums.Rol;
import com.company.proyecto_jsp.infrastructure.repository.CRUDUsuario;
import com.company.proyecto_jsp.infrastructure.services.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletUsuario", urlPatterns = "/usuario")
public class ServletUsuario extends HttpServlet {
    private final UsuarioService SERVICE = new UsuarioService(new CRUDUsuario());

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

        if ("listar_usuarios".equals(accion)) {
            validateSesion(request, response);
            processListarUsuarios(request, response, contextPath);
        } else if ("logout".equals(accion)) {
            processLogout(request, response, contextPath);
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
            case "login":
                processLogin(request, response, contextPath);
                break;
            case "agregar_usuario":
                processAgregarUsuario(request, response, contextPath);
                break;
            case "buscar_usuario":
                processBuscarUsuario(request, response, contextPath);
                break;
            case "cargar_editar_usuario":
                processCargarUsuarioParaEditar(request, response, contextPath);
                break;
            case "actualizar_usuario":
                processActualizarUsuario(request, response, contextPath);
                break;
            case "eliminar_usuario":
                processEliminarUsuario(request, response, contextPath);
                break;
            default:
                response.sendRedirect(contextPath + "/index.jsp");
        }
    }

    private void processLogin(HttpServletRequest request, HttpServletResponse response, String contextPath) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        try {
            Usuario usuario = SERVICE.getUsuarioByIdAndPassword(id, password);
            request.getSession().setAttribute("usuario", usuario);
            String mensaje = "Bienvenido has iniciado como " + usuario.getNombre();
            response.sendRedirect(contextPath + "/index.jsp?mensaje=" + mensaje);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            response.sendRedirect(contextPath + "/web/mensaje.jsp?mensaje=" + mensaje);
        }
    }

    private void processAgregarUsuario(HttpServletRequest request, HttpServletResponse response, String contextPath) throws ServletException, IOException {
        validateSesion(request, response);
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");
        try {
            Usuario usuario = new Usuario(id, nombre, correo, password, Rol.fromString(rol));
            SERVICE.addUsuario(usuario);
            String mensaje = "Usuario agregado con exito";
            response.sendRedirect(contextPath + "/web/usuario/agregar.jsp?mensaje=" + mensaje);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            response.sendRedirect(contextPath + "/web/usuario/agregar.jsp?mensaje=" + mensaje);
        }
    }

    private void processBuscarUsuario(HttpServletRequest request, HttpServletResponse response, String contextPath) throws ServletException, IOException {
        validateSesion(request, response);
        String id = request.getParameter("id");
        try {
            Usuario usuario = SERVICE.getUsuarioById(id);
            request.setAttribute("usuario.buscar", usuario);
            request.getRequestDispatcher("/web/usuario/buscar_eliminar.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect(contextPath + "/web/usuario/buscar_eliminar.jsp?mensaje=" + e.getMessage());
        }
    }

    private void processCargarUsuarioParaEditar(HttpServletRequest request, HttpServletResponse response, String contextPath) throws ServletException, IOException {
        validateSesion(request, response);
        String id = request.getParameter("id");
        try {
            Usuario usuario = SERVICE.getUsuarioById(id);
            request.setAttribute("usuario.editar", usuario);
            request.getRequestDispatcher("/web/usuario/actualizar.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect(contextPath + "/web/usuario/buscar_eliminar.jsp?mensaje=" + e.getMessage());
        }
    }

    private void processActualizarUsuario(HttpServletRequest request, HttpServletResponse response, String contextPath) throws ServletException, IOException {
        validateSesion(request, response);
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String new_password = request.getParameter("new_password");
        String rol = request.getParameter("rol");
        try {
            Usuario usuario_act;
            Usuario usuario = SERVICE.getUsuarioById(id);
            if (new_password == null || new_password.trim().isEmpty()) {
                usuario_act = new Usuario(id, nombre, correo, usuario.getPassword(), Rol.fromString(rol));
            } else {
                usuario_act = new Usuario(id, nombre, correo, new_password, Rol.fromString(rol));
            }
            SERVICE.updateUsuario(usuario_act);
            String mensaje = "Usuario actualizado con exito";
            response.sendRedirect(contextPath + "/web/usuario/buscar_eliminar.jsp?mensaje=" + mensaje);
        } catch (Exception e) {
            try {
                request.setAttribute("usuario.editar", new Usuario(id, nombre, correo, new_password, Rol.fromString(rol)));
            } catch (Exception ex) {
                String mensaje = e.getMessage();
                response.sendRedirect(contextPath + "/web/usuario/actualizar.jsp?mensaje=" + mensaje);
            }
            String mensaje = e.getMessage();
            response.sendRedirect(contextPath + "/web/usuario/actualizar.jsp?mensaje=" + mensaje);
        }
    }

    private void processEliminarUsuario(HttpServletRequest request, HttpServletResponse response, String contextPath) throws IOException {
        String id = request.getParameter("id");
        try {
            SERVICE.deleteUsuario(id);
            String mensaje = "Usuario eliminado con exito";
            response.sendRedirect(contextPath + "/web/usuario/buscar_eliminar.jsp?mensaje=" + mensaje);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            response.sendRedirect(contextPath + "/web/usuario/buscar_eliminar.jsp?mensaje=" + mensaje);
        }
    }

    private void processListarUsuarios(HttpServletRequest request, HttpServletResponse response, String contextPath) throws ServletException, IOException {
        validateSesion(request, response);
        try {
            List<Usuario> usuarios = SERVICE.getAllUsuarios();
            request.setAttribute("usuarios", usuarios);
        } catch (Exception e) {
            response.sendRedirect(contextPath + "/web/mensaje.jsp?mensaje=" + e.getMessage());
        }
        request.getRequestDispatcher("/web/usuario/listar.jsp").forward(request, response);
    }

    private void processLogout(HttpServletRequest request, HttpServletResponse response, String contextPath) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(contextPath + "/index.jsp");
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
