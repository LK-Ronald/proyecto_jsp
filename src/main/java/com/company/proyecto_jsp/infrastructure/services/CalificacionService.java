package com.company.proyecto_jsp.infrastructure.services;

import com.company.proyecto_jsp.domain.entities.Calificacion;
import com.company.proyecto_jsp.infrastructure.repository.CalificacionRepository;

import java.util.List;

public class CalificacionService {
    private final CalificacionRepository repository;

    public CalificacionService(CalificacionRepository repository) {
        this.repository = repository;
    }

    public void addCalificacion(Calificacion calificacion) throws Exception {
        repository.create(calificacion);
    }

    public void updateCalificacion(Calificacion calificacion) throws Exception {
        repository.update(calificacion);
    }

    public void deleteCalificacion(final int cid) throws Exception {
        repository.delete(cid);
    }

    public Calificacion getCalificacionById(int cid) throws Exception {
        return repository.getCalificacionById(cid);
    }

    public List<Calificacion> getAllCalificacion() throws Exception {
        List<Calificacion> calificaciones = repository.getAllCalificacion();
        return calificaciones;
    }
}
