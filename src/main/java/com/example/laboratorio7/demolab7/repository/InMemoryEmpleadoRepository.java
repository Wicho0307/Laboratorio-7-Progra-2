package com.example.laboratorio7.demolab7.repository;

import com.example.laboratorio7.demolab7.model.Empleado;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryEmpleadoRepository implements EmpleadoRepository {

    private final List<Empleado> empleados = new CopyOnWriteArrayList<>();
    private final AtomicLong secuenciaIds = new AtomicLong();

    @Override
    public Empleado guardar(Empleado empleado) {
        Empleado empleadoGuardado = new Empleado(
                secuenciaIds.incrementAndGet(),
                empleado.nombre(),
                empleado.apellido(),
                empleado.correo(),
                empleado.especialidad(),
                empleado.estado()
        );

        empleados.add(empleadoGuardado);
        return empleadoGuardado;
    }

    @Override
    public List<Empleado> buscarTodos() {
        return List.copyOf(empleados);
    }

    @Override
    public List<Empleado> buscarPorEspecialidad(String especialidad) {
        return empleados.stream()
                .filter(empleado -> empleado.especialidad().equalsIgnoreCase(especialidad))
                .toList();
    }

    @Override
    public Optional<Empleado> buscarPorId(Long id) {
        return empleados.stream()
                .filter(empleado -> empleado.id().equals(id))
                .findFirst();
    }
}
