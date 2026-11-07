package com.example.laboratorio7.demolab7.model;

public record Proveedor(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String categoria,
        String estado
) {
}
