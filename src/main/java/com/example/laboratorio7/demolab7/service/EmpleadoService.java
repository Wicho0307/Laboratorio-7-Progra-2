package com.example.laboratorio7.demolab7.service;

import com.example.laboratorio7.demolab7.dto.CrearEmpleadoRequest;
import com.example.laboratorio7.demolab7.exception.ResourceNotFoundException;
import com.example.laboratorio7.demolab7.model.Empleado;
import com.example.laboratorio7.demolab7.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado crear(CrearEmpleadoRequest request) {
        Empleado empleado = new Empleado(
                null,
                request.nombre().trim(),
                request.apellido().trim(),
                request.correo().trim().toLowerCase(Locale.ROOT),
                request.especialidad().trim(),
                request.estado().trim()
        );

        return empleadoRepository.guardar(empleado);
    }

    public List<Empleado> obtenerTodos(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            return empleadoRepository.buscarTodos();
        }

        return empleadoRepository.buscarPorEspecialidad(especialidad.trim());
    }

    public Empleado obtenerPorId(Long id) {
        return empleadoRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", id));
    }
}
