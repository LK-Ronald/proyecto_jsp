package com.company.proyecto_jsp.infrastructure.services;

import com.company.proyecto_jsp.domain.entities.Usuario;
import com.company.proyecto_jsp.infrastructure.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    public void addUsuario(Usuario usuario) throws Exception {
        validateId(usuario.getId());
        validatePassword(usuario.getPassword());
        repository.create(usuario);
    }

    public void deleteUsuario(final String id) throws Exception {
        validateId(id);
        repository.delete(id);
    }

    public void updateUsuario(Usuario usuario) throws Exception {
        validateId(usuario.getId());
        validatePassword(usuario.getPassword());
        repository.update(usuario);
    }

    public Usuario getUsuarioById(final String id) throws Exception {
        validateId(id);
        return repository.getUsuarioById(id);
    }

    public List<Usuario> getAllUsuarios() throws Exception {
        return repository.getAllUsuarios();
    }

    public Usuario getUsuarioByIdAndPassword(final String id, final String password) throws Exception {
        validateId(id);
        validatePassword(password);
        return repository.getUsuarioByIdAndPassword(id, password);
    }

    private void validateId(final String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new Exception("El ID es obligatorio");
        }
    }

    private void validatePassword(final String password) throws Exception {
        if (password == null || password.isEmpty()) {
            throw new Exception("La password es obligatoria");
        }
    }
}
