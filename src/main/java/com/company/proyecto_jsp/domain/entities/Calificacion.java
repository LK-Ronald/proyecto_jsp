package com.company.proyecto_jsp.domain.entities;

import java.util.Date;

public class Calificacion {
    private int cid;
    private Date fecha;
    private String estudiante;
    private String docente;
    private String asignatura;
    private String carrera;
    private String universidad;
    private String periodo;
    private String actividadEvaluada;
    private double porcentaje;
    private double nota;

    public Calificacion() {
    }

    public Calificacion(
            int cid,
            Date fecha,
            String estudiante,
            String docente,
            String asignatura,
            String carrera,
            String universidad,
            String periodo,
            String actividadEvaluada,
            double porcentaje, double nota) {
        this.cid = cid;
        this.fecha = fecha;
        this.estudiante = estudiante;
        this.docente = docente;
        this.asignatura = asignatura;
        this.carrera = carrera;
        this.universidad = universidad;
        this.periodo = periodo;
        this.actividadEvaluada = actividadEvaluada;
        this.porcentaje = porcentaje;
        this.nota = nota;
    }

    public int getCid() {
        return cid;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public String getDocente() {
        return docente;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getUniversidad() {
        return universidad;
    }

    public String getPeriodo() {
        return periodo;
    }

    public String getActividadEvaluada() {
        return actividadEvaluada;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public double getNota() {
        return nota;
    }
}
