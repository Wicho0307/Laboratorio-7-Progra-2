package com.example.laboratorio7.demolab7.repository;

import com.example.laboratorio7.demolab7.model.Profesor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryProfesorRepository implements ProfesorRepository {

    private final List<Profesor> profesores = new CopyOnWriteArrayList<>();
    private final AtomicLong secuenciaIds = new AtomicLong();

    @Override
    public Profesor guardar(Profesor profesor) {
        Profesor profesorGuardado = new Profesor(
                secuenciaIds.incrementAndGet(),
                profesor.nombre(),
                profesor.apellido(),
                profesor.correo(),
                profesor.especialidad(),
                profesor.estado()
        );

        profesores.add(profesorGuardado);
        return profesorGuardado;
    }

    @Override
    public List<Profesor> buscarTodos() {
        return List.copyOf(profesores);
    }

    @Override
    public List<Profesor> buscarPorEspecialidad(String especialidad) {
        return profesores.stream()
                .filter(profesor -> profesor.especialidad().equalsIgnoreCase(especialidad))
                .toList();
    }

    @Override
    public Optional<Profesor> buscarPorId(Long id) {
        return profesores.stream()
                .filter(profesor -> profesor.id().equals(id))
                .findFirst();
    }
}
