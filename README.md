# Sistema de Gestión Web - Proyecto JSP

Aplicación web desarrollada en **Java (Jakarta EE)** con **JSP** y **Servlets**, conectada a una base de datos
**PostgreSQL** mediante JDBC. Este proyecto fue desarrollado para la asignatura *Desarrollo de Software Web* (Semestre
4 - Universidad de Cartagena).

---

## 📌 Descripción del Proyecto

El sistema es una solución web para la administración de usuarios y el registro de calificaciones académicas. Implementa
una arquitectura por capas (**Dominio**, **Infraestructura**, **Controladores** y **Vistas**) que desacopla la lógica de
negocio, el acceso a datos y la capa de presentación.

---

## ⚙️ Funcionalidades Principales

### 1. Autenticación y Control de Acceso

- **Inicio y Cierre de Sesión:** Autenticación de usuarios con gestión de sesiones HTTP.
- **Filtro de Seguridad (`AuthFilter`):** Protección de rutas privadas (`/web/usuario/*` y `/web/calificacion/*`),
  restringiendo el acceso únicamente a usuarios autenticados.

### 2. Módulo de Usuarios (CRUD)

- **Registrar Usuario:** Creación de nuevos usuarios con identificación, nombre, correo, contraseña y rol (`Usuario` o
  `Administrador`).
- **Consultar Usuario:** Búsqueda individual de usuarios por su ID.
- **Actualizar Usuario:** Modificación de datos y credenciales de acceso.
- **Eliminar Usuario:** Eliminación de usuarios del sistema.
- **Listar Usuarios:** Visualización tabular de todos los usuarios registrados.

### 3. Módulo de Calificaciones (CRUD)

- **Registrar Calificación:** Ingreso de evaluaciones académicas (estudiante, docente, asignatura, carrera, universidad,
  periodo, actividad evaluada y nota).
- **Consultar Calificación:** Búsqueda de calificaciones por su identificador (`CID`).
- **Actualizar Calificación:** Modificación de los datos y nota de la actividad evaluada.
- **Eliminar Calificación:** Eliminación de registros de calificaciones.
- **Listar Calificaciones:** Visualización tabular consolidada de todas las calificaciones existentes.

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java
- **Tecnologías Web:** Jakarta Servlet 6.1, JavaServer Pages (JSP), JSTL
- **Base de Datos:** PostgreSQL con JDBC (`PreparedStatement`)
- **Gestor de Dependencias y Construcción:** Apache Maven (con Maven Wrapper `mvnw`)
- **Servidor de Aplicaciones:** Compatible con Apache Tomcat 10.1+

### El archivo README lo hizo Antigravity, ya que tengo plan plus y lo estoy estrenando. AJAJJAJAJA