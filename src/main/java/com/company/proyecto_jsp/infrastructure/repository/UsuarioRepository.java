package com.company.proyecto_jsp.infrastructure.repository;

import com.company.proyecto_jsp.domain.entities.Usuario;

import java.util.List;

public interface UsuarioRepository {
    void create(final Usuario usuario) throws Exception;

    void delete(final String id) throws Exception;

    void update(final Usuario usuario) throws Exception;

    Usuario getUsuarioById(final String id) throws Exception;

    Usuario getUsuarioByIdAndPassword(final String id, final String password) throws Exception;

    List<Usuario> getAllUsuarios() throws Exception;
}
