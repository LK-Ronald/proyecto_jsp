package com.company.proyecto_jsp.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConeccion {

    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_HOST = System.getenv("DB_HOST");
    private static final String DB_NAME = System.getenv("DB_NAME");
    private static final String DB_PASS = System.getenv("DB_PASS");
    private static final String DB_PORT = System.getenv("DB_PORT");
    private static final String DB_URL = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    private static final String DRIVER = "com.postgresql.Driver";

    private static Connection conexion;

    public DBConeccion() throws SQLException {
        iniciarConexion();
    }

    private static void iniciarConexion() throws SQLException {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error: Driver PostgreSQL no encontrado.");
        } catch (SQLException e) {
            String mensaje = "Error: No se pudo establecer la conexion con la base de datos.";
            throw new SQLException(mensaje);
        }
    }

    public static Connection getConexion() throws SQLException {
        if (conexion == null) {
            iniciarConexion();
            return conexion;
        }
        return conexion;
    }

    public static void closeConnection() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}
