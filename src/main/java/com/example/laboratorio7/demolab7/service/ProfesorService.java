package com.example.laboratorio7.demolab7.service;

import com.example.laboratorio7.demolab7.dto.CrearProfesorRequest;
import com.example.laboratorio7.demolab7.exception.ResourceNotFoundException;
import com.example.laboratorio7.demolab7.model.Profesor;
import com.example.laboratorio7.demolab7.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public Profesor crear(CrearProfesorRequest request) {
        Profesor profesor = new Profesor(
                null,
                request.nombre().trim(),
                request.apellido().trim(),
                request.correo().trim().toLowerCase(Locale.ROOT),
                request.especialidad().trim(),
                request.estado().trim()
        );

        return profesorRepository.guardar(profesor);
    }

    public List<Profesor> obtenerTodos(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            return profesorRepository.buscarTodos();
        }

        return profesorRepository.buscarPorEspecialidad(especialidad.trim());
    }

    public Profesor obtenerPorId(Long id) {
        return profesorRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor", id));
    }
}
