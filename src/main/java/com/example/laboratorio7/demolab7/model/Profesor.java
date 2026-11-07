package com.example.laboratorio7.demolab7.model;

public record Profesor(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String especialidad,
        String estado
) {
}
