package com.company.proyecto_jsp.infrastructure.repository;

import com.company.proyecto_jsp.domain.entities.Usuario;
import com.company.proyecto_jsp.domain.enums.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDUsuario implements UsuarioRepository {
    private final String TB_NOMBRE = "tb_usuarios";
    private final String SQL_INSERT = "INSERT INTO " + TB_NOMBRE + " (id, nombre, correo, password, rol) VALUES (?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE " + TB_NOMBRE + " SET nombre = ?, correo = ?, password = ?, rol = ? WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM " + TB_NOMBRE + " WHERE id = ?";
    private final String SQL_SELECT_ID = "SELECT * FROM " + TB_NOMBRE + " WHERE id = ?";
    private final String SQL_SELECT_ALL = "SELECT * FROM " + TB_NOMBRE;
    private final String SQL_SELECT_SECCION = "SELECT * FROM " + TB_NOMBRE + " WHERE id=? AND password=?";

    @Override
    public void create(final Usuario usuario) throws Exception {
        try (Connection conexion = DBConexion.getConexion();
             PreparedStatement statement = conexion.prepareStatement(SQL_INSERT)) {
            statement.setString(1, usuario.getId());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getCorreo());
            statement.setString(4, usuario.getPassword());
            statement.setString(5, usuario.getRol().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                throw new Exception("El usuario con el codigo " + usuario.getId() + " ya existe");
            } else {
                throw e;
            }
        } finally {
            DBConexion.closeConnection();
        }
    }

    @Override
    public void delete(final String id) throws Exception {
        try (Connection conexion = DBConexion.getConexion();
             PreparedStatement statement = conexion.prepareStatement(SQL_DELETE)) {
            statement.setString(1, id);
            int rowAffect = statement.executeUpdate();
            if (rowAffect == 0) {
                throw new Exception("El usuario con id " + id + " no existe");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            DBConexion.closeConnection();
        }
    }

    @Override
    public void update(final Usuario usuario) throws Exception {
        try (Connection conexion = DBConexion.getConexion();
             PreparedStatement statement = conexion.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getCorreo());
            statement.setString(3, usuario.getPassword());
            statement.setString(4, usuario.getRol().name());
            statement.setString(5, usuario.getId());
            int rowAffect = statement.executeUpdate();
            if (rowAffect == 0) {
                throw new Exception("El usuario con id " + usuario.getId() + " no existe");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            DBConexion.closeConnection();
        }
    }

    @Override
    public Usuario getUsuarioById(final String id) throws Exception {
        Usuario usuario = null;
        try (Connection conexion = DBConexion.getConexion();
             PreparedStatement statement = conexion.prepareStatement(SQL_SELECT_ID)) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                usuario = mapResultSetToUsuario(rs);
            } else {
                throw new Exception("El usuario con id " + id + " no existe");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            DBConexion.closeConnection();
        }
        return usuario;
    }

    @Override
    public Usuario getUsuarioByIdAndPassword(final String id, final String password) throws Exception {
        Usuario usuario = null;
        try (Connection conexion = DBConexion.getConexion();
             PreparedStatement statement = conexion.prepareStatement(SQL_SELECT_SECCION)) {
            statement.setString(1, id);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                usuario = mapResultSetToUsuario(rs);
            } else {
                throw new Exception("El usuario con id " + id + " no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConexion.closeConnection();
        }
        return usuario;
    }

    @Override
    public List<Usuario> getAllUsuarios() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conexion = DBConexion.getConexion();
             PreparedStatement statement = conexion.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                usuarios.add(mapResultSetToUsuario(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConexion.closeConnection();
        }
        return usuarios;
    }

    private Usuario mapResultSetToUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getString("id"),
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("password"),
                Rol.valueOf(rs.getString("rol").toUpperCase())
        );
    }
}
