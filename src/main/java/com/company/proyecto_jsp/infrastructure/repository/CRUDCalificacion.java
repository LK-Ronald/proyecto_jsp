package com.company.proyecto_jsp.infrastructure.repository;

import com.company.proyecto_jsp.domain.entities.Calificacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDCalificacion implements CalificacionRepository {
    private final String TB_NOMBRE = "tb_calificaciones";
    private final String SQL_INSERT = "INSERT INTO " + TB_NOMBRE + " (fecha, estudiante, docente, asignatura, carrera, universidad, periodo, actividadEvaluada, nota) VALUES (CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE " + TB_NOMBRE + " SET estudiante=?, docente=?, asignatura=?, carrera=?, universidad=?, periodo=?, actividadEvaluada=?, nota=? WHERE cid=?";
    private final String SQL_DELETE = "DELETE FROM " + TB_NOMBRE + " WHERE cid = ?";
    private final String SQL_SELECT_ID = "SELECT * FROM " + TB_NOMBRE + " WHERE cid = ?";
    private final String SQL_SELECT_ALL = "SELECT * FROM " + TB_NOMBRE;


    @Override
    public void create(Calificacion calificacion) throws Exception {
        try (Connection conexion = DBConexion.getConexion(); PreparedStatement statement = conexion.prepareStatement(SQL_INSERT)) {
            statement.setString(1, calificacion.getEstudiante());
            statement.setString(2, calificacion.getDocente());
            statement.setString(3, calificacion.getAsignatura());
            statement.setString(4, calificacion.getCarrera());
            statement.setString(5, calificacion.getUniversidad());
            statement.setString(6, calificacion.getPeriodo());
            statement.setString(7, calificacion.getActividadEvaluada());
            statement.setDouble(8, calificacion.getNota());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error en la db al insertar la calificacion");
        } finally {
            DBConexion.closeConnection();
        }
    }

    @Override
    public void delete(int cid) throws Exception {
        try (Connection conexion = DBConexion.getConexion(); PreparedStatement statement = conexion.prepareStatement(SQL_DELETE)) {
            statement.setInt(1, cid);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error en la db al eliminar la calificacion");
        } finally {
            DBConexion.closeConnection();
        }
    }

    @Override
    public void update(Calificacion calificacion) throws Exception {
        try (Connection conexion = DBConexion.getConexion(); PreparedStatement statement = conexion.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, calificacion.getEstudiante());
            statement.setString(2, calificacion.getDocente());
            statement.setString(3, calificacion.getAsignatura());
            statement.setString(4, calificacion.getCarrera());
            statement.setString(5, calificacion.getUniversidad());
            statement.setString(6, calificacion.getPeriodo());
            statement.setString(7, calificacion.getActividadEvaluada());
            statement.setDouble(8, calificacion.getNota());
            statement.setInt(9, calificacion.getCid());
            int resultSet = statement.executeUpdate();
            if (resultSet == 0) {
                throw new Exception("No se encontro la calificacion con el id " + calificacion.getCid());
            }
        } catch (Exception e) {
            throw new Exception("Error en la db al actualizar la calificacion");
        } finally {
            DBConexion.closeConnection();
        }
    }

    @Override
    public Calificacion getCalificacionById(int cid) throws Exception {
        Calificacion calificacion = null;
        try (Connection conexion = DBConexion.getConexion(); PreparedStatement statement = conexion.prepareStatement(SQL_SELECT_ID)) {
            statement.setInt(1, cid);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                calificacion = mapResultSetToCalificacion(result);
            } else {
                throw new Exception("No se encontro la calificacion con el id " + cid);
            }
        } catch (Exception e) {
            throw new Exception("Error en la db al obtener la calificacion");
        } finally {
            DBConexion.closeConnection();
        }
        return calificacion;
    }

    @Override
    public List<Calificacion> getAllCalificacion() throws Exception {
        List<Calificacion> calificaciones = new ArrayList<>();
        try (Connection conexion = DBConexion.getConexion(); PreparedStatement statement = conexion.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                calificaciones.add(mapResultSetToCalificacion(resultSet));
            }
        } catch (Exception e) {
            throw new Exception("Error en la db al obtener las calificaciones");
        } finally {
            DBConexion.closeConnection();
        }
        return calificaciones;
    }

    private Calificacion mapResultSetToCalificacion(ResultSet rs) throws SQLException {
        return new Calificacion(
                rs.getInt("cid"),
                rs.getString("estudiante"),
                rs.getString("docente"),
                rs.getString("asignatura"),
                rs.getString("carrera"),
                rs.getString("universidad"),
                rs.getString("periodo"),
                rs.getString("actividadEvaluada"),
                rs.getDouble("nota")
        );
    }
}
