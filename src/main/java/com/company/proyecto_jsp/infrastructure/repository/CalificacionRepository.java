package com.company.proyecto_jsp.infrastructure.repository;

import com.company.proyecto_jsp.domain.entities.Calificacion;

import java.util.List;

public interface CalificacionRepository {

    void create(final Calificacion calificacion) throws Exception;

    void delete(final int cid) throws Exception;

    void update(final Calificacion calificacion) throws Exception;

    Calificacion getCalificacionById(final int cid) throws Exception;

    List<Calificacion> getAllCalificacion() throws Exception;

}
