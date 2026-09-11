package com.company.proyecto_jsp.domain.entities;

import java.time.LocalDate;

public class Calificacion {
    private int cid;
    private LocalDate fecha;
    private String estudiante;
    private String docente;
    private String asignatura;
    private String carrera;
    private String universidad;
    private String periodo;
    private String actividadEvaluada;
    private double nota;

    public Calificacion() {
    }

    public Calificacion(
            int cid,
            String estudiante,
            String docente,
            String asignatura,
            String carrera,
            String universidad,
            String periodo,
            String actividadEvaluada,
            double nota) {
        this.cid = cid;
        this.fecha = LocalDate.now();
        this.estudiante = estudiante;
        this.docente = docente;
        this.asignatura = asignatura;
        this.carrera = carrera;
        this.universidad = universidad;
        this.periodo = periodo;
        this.actividadEvaluada = actividadEvaluada;
        this.nota = nota;
    }

    public int getCid() {
        return cid;
    }

    public LocalDate getFecha() {
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

    public double getNota() {
        return nota;
    }
}
