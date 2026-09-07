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

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletUsuario", urlPatterns = "/usuario")
public class ServletUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String contextPath = request.getContextPath();
        String accion = request.getParameter("accion");

        if (accion == null || accion.trim().isEmpty()) {
            response.sendRedirect(contextPath + "/index.jsp");
            return;
        }

        PrintWriter out = response.getWriter();
        UsuarioService service = new UsuarioService(new CRUDUsuario());

        switch (accion) {
            case "login":
                processLogin(request, response, service, contextPath);
                break;
            case "agregar_usuario":
                processAgregarUsuario(request, response, service, contextPath);
                break;
            case "buscar_usuario":
                processBuscarUsuario(request, response, service, contextPath);
                break;
            case "cargar_editar_usuario":
                processCargarUsuarioParaEditar(request, response, service, contextPath);
                break;
            case "actualizar_usuario":
                processActualizarUsuario(request, response, service, contextPath);
                break;
            case "eliminar_usuario":
                processEliminarUsuario(request, response, service, contextPath);
                break;
            default:
                response.sendRedirect(contextPath + "/index.jsp");
        }
    }

    private void processLogin(HttpServletRequest request, HttpServletResponse response, UsuarioService service, String contextPath) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        try {
            Usuario usuario = service.getUsuarioByIdAndPassword(id, password);
            request.getSession().setAttribute("usuario", usuario);
            String mensaje = "Bienvenido has iniciado como " + usuario.getNombre();
            response.sendRedirect(contextPath + "/index.jsp?mensaje=" + mensaje);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            response.sendRedirect(contextPath + "/web/mensaje.jsp?mensaje=" + mensaje);
        }
    }

    private void processAgregarUsuario(HttpServletRequest request, HttpServletResponse response, UsuarioService service, String contextPath) throws ServletException, IOException {
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");
        try {
            Usuario usuario = new Usuario(id, nombre, correo, password, Rol.fromString(rol));
            service.addUsuario(usuario);
            String mensaje = "Usuario agregado con exito";
            response.sendRedirect(contextPath + "/web/usuario/agregar.jsp?mensaje=" + mensaje);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            response.sendRedirect(contextPath + "/web/usuario/agregar.jsp?mensaje=" + mensaje);
        }
    }

    private void processBuscarUsuario(HttpServletRequest request, HttpServletResponse response, UsuarioService service, String contextPath) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            Usuario usuario = service.getUsuarioById(id);
            request.setAttribute("usuario.buscar", usuario);
            request.getRequestDispatcher("/web/usuario/buscar_eliminar.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect(contextPath + "/web/usuario/buscar_eliminar.jsp?mensaje=" + e.getMessage());
        }
    }

    private void processCargarUsuarioParaEditar(HttpServletRequest request, HttpServletResponse response, UsuarioService service, String contextPath) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            Usuario usuario = service.getUsuarioById(id);
            request.setAttribute("usuario.editar", usuario);
            request.getRequestDispatcher("/web/usuario/actualizar.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect(contextPath + "/web/usuario/buscar_eliminar.jsp?mensaje=" + e.getMessage());
        }
    }

    private void processActualizarUsuario(HttpServletRequest request, HttpServletResponse response, UsuarioService service, String contextPath) throws ServletException, IOException {
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String new_password = request.getParameter("new_password");
        String rol = request.getParameter("rol");
        try {
            Usuario usuario_act;
            Usuario usuario = service.getUsuarioById(id);
            if (new_password == null || new_password.trim().isEmpty()) {
                usuario_act = new Usuario(id, nombre, correo, usuario.getPassword(), Rol.fromString(rol));
            } else {
                usuario_act = new Usuario(id, nombre, correo, new_password, Rol.fromString(rol));
            }
            service.updateUsuario(usuario_act);
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

    private void processEliminarUsuario(HttpServletRequest request, HttpServletResponse response, UsuarioService service, String contextPath) throws IOException {
        String id = request.getParameter("id");
        try {
            service.deleteUsuario(id);
            String mensaje = "Usuario eliminado con exito";
            response.sendRedirect(contextPath + "/web/usuario/buscar_eliminar.jsp?mensaje=" + mensaje);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            response.sendRedirect(contextPath + "/web/usuario/buscar_eliminar.jsp?mensaje=" + mensaje);
        }
    }
}
