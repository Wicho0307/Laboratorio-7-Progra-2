package com.example.laboratorio7.demolab7.model;

public record Empleado(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String especialidad,
        String estado
) {
}
