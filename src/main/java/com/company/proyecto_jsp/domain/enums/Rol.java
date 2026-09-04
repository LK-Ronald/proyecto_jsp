package com.company.proyecto_jsp.domain.enums;

public enum Rol {
    USUARIO,
    ADMINISTRADOR;

    public static Rol fromString(final String value) throws Exception {
        for (final Rol rol : values()) {
            if (rol.name().equalsIgnoreCase(value)) {
                return rol;
            }
        }
        throw new Exception("El rol asignado no es valido");
    }
}
